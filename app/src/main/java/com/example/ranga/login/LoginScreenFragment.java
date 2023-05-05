package com.example.ranga.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.ranga.EditTextFragment;
import com.example.ranga.R;

import java.util.regex.Pattern;

public class LoginScreenFragment extends EditTextFragment
{
    private TextView loginText;
    private TextView passText;
    private Button loginBtn;
    private Button createBtn;
    private Pattern loginPattern = Pattern.compile("^(?=.*[a-zA-Z])[a-zA-Z0-9_]{3,}$");
    private Pattern passPattern = Pattern.compile(".{8,}");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.login_screen, container, false);
        loginText = (TextView) v.findViewById(R.id.login_screen_login_text);
        passText = (TextView) v.findViewById(R.id.login_screen_pass_text);
        loginBtn = (Button) v.findViewById(R.id.login_screen_sing_in_btn);
        createBtn = (Button) v.findViewById(R.id.login_screen_create_acc_btn);

        LoginFragmentViewModel model = new ViewModelProvider(this).get(LoginFragmentViewModel.class);
        model.getReadys().observe(getViewLifecycleOwner(), new Observer<boolean[]>() {
            @Override
            public void onChanged(boolean[] booleans) {
                for (int i = 0; i < booleans.length; i++)
                {
                    if (!booleans[i])
                    {
                        loginBtn.setEnabled(false);
                        return;
                    }
                }
                loginBtn.setEnabled(true);
            }
        });

        SetInputViewPatterListener(loginText, loginPattern, 0, model);
        SetInputViewPatterListener(passText, passPattern, 1, model);

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
                model.onLoginBtnWasClicked(view, loginText.getText().toString(), passText.getText().toString());
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
//      SharedPreferences pref = getContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
//      String login = pref.getString("LastLogin", "");
//      loginText.setText(login);
    }


}
