package com.example.ranga.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ranga.EditTextFragment;
import com.example.ranga.R;
import com.example.ranga.main.ChoiseAvatarDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateAccFragment extends EditTextFragment
{
    private CircleImageView avatar;
    private TextView loginText;
    private TextView emailText;
    private TextView passText;
    private TextView passConfText;
    private Button createBtn;
    private int avatarId = 0;
    private Pattern loginPattern = Pattern.compile("^(?=.*[a-zA-Z])[a-zA-Z0-9_]{3,}$");
    private Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private Pattern passPattern = Pattern.compile(".{5,}");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.create_acc_scree, container, false);
        avatar = (CircleImageView) v.findViewById(R.id.create_acc_avatar);
        loginText = (TextView) v.findViewById(R.id.create_acc_login_text);
        emailText = (TextView) v.findViewById(R.id.create_acc_email_text);
        passText = (TextView) v.findViewById(R.id.create_acc_pass_text);
        passConfText = (TextView) v.findViewById(R.id.create_acc_pass_confirm_text);
        createBtn = (Button) v.findViewById(R.id.create_acc_create_btn);

        CreateAccFragmentViewModel model = new ViewModelProvider(this).get(CreateAccFragmentViewModel.class);

        SetInputViewPatterListener(loginText, loginPattern, 1, model);
        SetInputViewPatterListener(emailText, emailPattern, 2, model);
        SetInputViewPatterListener(passText, passPattern, 3, model);
        SetInputViewPatterListener(passConfText, passPattern, 4, model);
        model.getReadys().observe(getViewLifecycleOwner(), new Observer<boolean[]>() {
            @Override
            public void onChanged(boolean[] booleans)
            {
                for (int i = 0; i < booleans.length; i++)
                {
                    if (!booleans[i])
                    {
                        createBtn.setEnabled(false);
                        return;
                    }
                }
                createBtn.setEnabled(true);
            }
        });


        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String pass1 = passText.getText().toString();
                String pass2 = passConfText.getText().toString();
                if (pass1.equals(pass2))
                    model.OnCreateAccBtnWasClicked(view, loginText.getText().toString(), emailText.getText().toString(), pass1, avatarId);
                else
                    Toast.makeText(getContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChoiseAvatarDialog dialog = new ChoiseAvatarDialog(getContext(), new ChoiseAvatarDialog.OnAvatarSelectedListener() {
                    @Override
                    public void onAvatarSelected(int avatarResId)
                    {
                        if (avatar.getBackground() != null)
                            avatar.setBackground(null);
                        avatar.setImageResource(avatarResId);
                        avatarId = avatarResId;
                        model.SetReadyStatus(0, true);
                    }
                });
                dialog.show();
            }
        });

        return v;
    }

}
