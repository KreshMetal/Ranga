package com.example.ranga.main.comixList;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.ranga.R;
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
        Comix comix = new Comix();
        comix.nameRus = name;
        comix.nameEng = engName;
        comix.desc = desc;
        comix.author = author;

        CopyComixTask copyComixTask = new CopyComixTask(mFragmnet.getActivity().getApplicationContext(),comix);
        copyComixTask.execute();

    }

    private void SaveNewComixInBd(Comix comix)
    {
        ComixTableQueriesHelper.InsertComixInBd(comix);
    }

    private class CopyComixTask extends AsyncTask<Void, Void, File>
    {
        private Dialog progressDialog;
        private Comix comix;
        private Context context;
        public CopyComixTask(Context context, Comix comix)
        {
            this.context = context;
            this.comix = comix;
            progressDialog = new Dialog(mFragmnet.getActivity());
            progressDialog.setContentView(R.layout.progress_bar_dialog);
            progressDialog.setCancelable(false);
        }
        @Override
        protected File doInBackground(Void... voids)
        {
            return FilesManager.copyDirectoryToFolder(context, folderPath, context.getFilesDir());
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(File folder)
        {
            super.onPostExecute(folder);
            FilesManager.copyFileToFolder(context, logoPath, folder, "logo.png");
            comix.nameFolder = folder.getPath();
            comix.size = folder.listFiles().length;
            SaveNewComixInBd(comix);
            progressDialog.dismiss();
            Navigation.findNavController(mFragmnet.getActivity(), R.id.main_nav_host_fragment).navigate(R.id.action_addComixFragment_to_comixListFragment);
        }
    }


    public static class CopyComixWorker extends Worker
    {
        public static final String URI_IN_FOLDER = "FOLDER_URI";
        public static final String URI_IN_IMAGE = "IMAGE_URI";
        public static final String KEY_OUT_FOLDER = "FOLDER_PATH";
        public static final String KEY_OUT_SIZE = "FOLDER_SIZE";

        public CopyComixWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
            super(context, workerParams);
        }

        @NonNull
        @Override
        public Result doWork()
        {
            Uri folderPath = Uri.parse(getInputData().getString(URI_IN_FOLDER));
            Uri logoPath = Uri.parse(getInputData().getString(URI_IN_IMAGE));
            File folder = FilesManager.copyDirectoryToFolder(getApplicationContext(), folderPath, getApplicationContext().getFilesDir());
            FilesManager.copyFileToFolder(getApplicationContext(), logoPath, folder, "logo.png");

            Data output = new Data.Builder()
                    .putString(KEY_OUT_FOLDER, folder.getPath())
                    .putInt(KEY_OUT_SIZE, folder.listFiles().length)
                    .build();

            return Result.success(output);
        }
    }
}

