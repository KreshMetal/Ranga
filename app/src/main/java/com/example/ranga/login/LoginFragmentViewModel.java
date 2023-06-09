package com.example.ranga.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.example.ranga.App;
import com.example.ranga.EditTextFragmentViewModel;
import com.example.ranga.database.User;
import com.example.ranga.database.UsersTableQueriesHelper;
import com.example.ranga.main.MainActivity;

import java.util.List;

public class LoginFragmentViewModel extends EditTextFragmentViewModel
{

    public LoginFragmentViewModel()
    {
        readys.postValue(new boolean[] {false, false});
    }

    private User CheckLoginAndPass(String login, String pass)
    {
        User user = UsersTableQueriesHelper.GetUserFromBd(login);
        if (user != null && user.pass.equals(pass)) return user;
        return null;
    }
    public void onLoginBtnWasClicked(View view, String login, String pass)
    {
        User user = CheckLoginAndPass(login, pass);
        if (user == null) Toast.makeText(view.getContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
        else
        {
            Toast.makeText(view.getContext(), "Добро пожаловать!", Toast.LENGTH_SHORT).show();
            SharedPreferences pref = view.getContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("LastLogin", user.login);
            editor.apply();
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            App.getInstance().setUser(user);
            view.getContext().startActivity(intent);
        }
    }
}
