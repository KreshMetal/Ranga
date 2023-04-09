package com.example.ranga.login;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.ranga.App;
import com.example.ranga.database.User;
import com.example.ranga.database.UserDao;

import java.util.List;

public class LoginFragmentPresenter
{
    private final LoginScreenFragment mFragment;
    private final UsersTableQueriesHelper helper;

    public LoginFragmentPresenter(LoginScreenFragment mFragment)
    {
        this.mFragment = mFragment;
        helper = new UsersTableQueriesHelper(App.getInstance().getDatabase().userDao());
        List<User> users = helper.GetUsersFromBd();
        for (User user:users)
        {
            Log.d("Test", user.id + "");
            Log.d("Test", user.login + "");
        }
    }

    private long CheckLoginAndPass(String login, String pass)
    {
        User user = helper.GetUserFromBd(login);
        //Log.d("TEST", user.login);
        //Log.d("TEST", user.pass);
        if (user != null && user.pass.equals(pass)) return user.id;
        return -1;
    }
    public void onLoginBtnWasClicked(String login, String pass)
    {
        long id = CheckLoginAndPass(login, pass);
        if (id == -1) Toast.makeText(mFragment.getContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
        else Toast.makeText(mFragment.getContext(), "Добро пожаловать!", Toast.LENGTH_SHORT).show();
    }
}
