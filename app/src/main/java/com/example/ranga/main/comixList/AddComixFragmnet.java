package com.example.ranga.main.comixList;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ranga.EditTextFragment;
import com.example.ranga.R;

import java.util.regex.Pattern;

public class AddComixFragmnet extends EditTextFragment
{
    AddComixFragmentPresenter presenter;
    ImageView logo;
    TextView blankLogoText;
    TextView folder;
    EditText name;
    EditText engName;
    EditText desc;
    EditText author;
    Button choiseFolderBtn;
    Button save;
    private Pattern namePattenr = Pattern.compile(".{2,}");
    private Pattern descPattenr = Pattern.compile(".{10,}");



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.add_new_comix_screen, container, false);
        presenter = new AddComixFragmentPresenter(this);
        logo = (ImageView) v.findViewById(R.id.add_comix_logo);
        blankLogoText = (TextView) v.findViewById(R.id.add_comix_logo_blank_logo_text);
        folder = (TextView) v.findViewById(R.id.add_comix_folder);
        name = (EditText) v.findViewById(R.id.add_comix_name);
        engName = (EditText) v.findViewById(R.id.add_comix_name_eng);
        desc = (EditText) v.findViewById(R.id.add_comix_desc);
        author = (EditText) v.findViewById(R.id.add_comix_author);
        choiseFolderBtn = (Button) v.findViewById(R.id.add_comix_coise_folder_btn);
        save = (Button) v.findViewById(R.id.add_comix_save_btn);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.OnLogoClicked();
            }
        });

        choiseFolderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.OnChoiseFolderBtnClicked();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.OnSaveBtnClicked(name.getText().toString(),
                        engName.getText().toString(),
                        desc.getText().toString(),
                        author.getText().toString());
            }
        });
        return v;
    }

    public void SetFolderText(String path)
    {
        folder.setText("Каталог с комиксом:" + path);
    }

    public void SetLogo(Uri uri)
    {
        if (uri == null) return;
        logo.setBackground(null);
        blankLogoText.setVisibility(View.GONE);
        logo.setImageURI(uri);
    }
}
