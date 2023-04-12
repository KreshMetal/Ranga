package com.example.ranga.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ranga.R;

public class LoginScreenFragment extends Fragment
{
    private LoginFragmentPresenter mPresenter;
    private TextView loginText;
    private TextView passText;
    private Button loginBtn;
    private Button createBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.login_screen, container, false);
        loginText = (TextView) v.findViewById(R.id.login_screen_login_text);
        passText = (TextView) v.findViewById(R.id.login_screen_pass_text);
        loginBtn = (Button) v.findViewById(R.id.login_screen_sing_in_btn);
        createBtn = (Button) v.findViewById(R.id.login_screen_create_acc_btn);

        mPresenter = new LoginFragmentPresenter(this);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginScreenFragment_to_createAccFragment);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mPresenter.onLoginBtnWasClicked(loginText.getText().toString(), passText.getText().toString());
            }
        });
        return v;
    }
}
