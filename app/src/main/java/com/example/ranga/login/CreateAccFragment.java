package com.example.ranga.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ranga.R;

public class CreateAccFragment extends Fragment
{
    CreateAccFragmentPresenter presenter;
    private TextView loginText;
    private TextView emailText;
    private TextView passText;
    private TextView passConfText;
    private Button createBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.create_acc_scree, container, false);
        presenter = new CreateAccFragmentPresenter(this);
        loginText = (TextView) v.findViewById(R.id.create_acc_login_text);
        emailText = (TextView) v.findViewById(R.id.create_acc_email_text);
        passText = (TextView) v.findViewById(R.id.create_acc_pass_text);
        passConfText = (TextView) v.findViewById(R.id.create_acc_pass_confirm_text);
        createBtn = (Button) v.findViewById(R.id.create_acc_create_btn);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String pass1 = passText.getText().toString();
                String pass2 = passConfText.getText().toString();
                if (pass1.equals(pass2))
                    presenter.OnCreateAccBtnWasClicked(view, loginText.getText().toString(), emailText.getText().toString(), pass1);
                else
                    Toast.makeText(getContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}
