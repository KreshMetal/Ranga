package com.example.ranga.main.comixList;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.ranga.database.Comix;
import com.example.ranga.database.ComixTableQueriesHelper;
import com.example.ranga.main.FilesManager;
import java.io.File;

public class AddComixFragmentPresenter {
    private final AddComixFragmnet mFragmnet;
    private final ActivityResultLauncher<Intent> getChoiseLogoResult;
    private final ActivityResultLauncher<Intent> getChoiseFolderResult;
    private Uri logoPath;
    private Uri folderPath;
    private String folderName;

    public AddComixFragmentPresenter(AddComixFragmnet fragmnet) {
        mFragmnet = fragmnet;
        getChoiseLogoResult = fragmnet.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (uri != null) {
                        mFragmnet.SetLogo(uri);
                    }
                    logoPath = uri;
                }
            }
        });

        getChoiseFolderResult = fragmnet.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Uri uri = result.getData().getData();
                    folderPath = uri;
                    mFragmnet.SetFolderText(folderPath.getPath());
                }
            }
        });
    }

    public void OnLogoClicked() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        getChoiseLogoResult.launch(photoPickerIntent);
    }

    public void OnChoiseFolderBtnClicked() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        getChoiseFolderResult.launch(intent);
    }

    public void OnSaveBtnClicked(String name, String engName, String desc, String author)
    {
        File folder = FilesManager.copyDirectoryToAppFilesDir(mFragmnet.getContext(), folderPath, mFragmnet.getContext().getFilesDir());
        FilesManager.copyFileToFolder(mFragmnet.getContext(), logoPath, folder, "logo.png");
        folderName = folder.getName();
        Comix comix = new Comix();
        comix.nameRus = name;
        comix.nameEng = engName;
        comix.desc = desc;
        comix.author = author;
        comix.nameFolder = folder.getPath();
        comix.size = folder.listFiles().length;
        ComixTableQueriesHelper.InsertComixInBd(comix);
    }
}