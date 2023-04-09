package com.example.ranga.login;

import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.ranga.App;
import com.example.ranga.R;
import com.example.ranga.database.User;
import com.example.ranga.database.UserDao;

public class CreateAccFragmentPresenter
{
    private final CreateAccFragment mFragment;
    private final UsersTableQueriesHelper helper;


    public CreateAccFragmentPresenter(CreateAccFragment fragment)
    {
        mFragment = fragment;
        helper = new UsersTableQueriesHelper(App.getInstance().getDatabase().userDao());
    }

    private boolean CheckLoginOnBd(String login)
    {
        User user = helper.GetUserFromBd(login);
        return user != null;
    }

    public void OnCreateAccBtnWasClicked(View view, String login, String email, String pass)
    {
        if (CheckLoginOnBd(login))
        {
            Toast.makeText(mFragment.getContext(), "Аккаунт с таким логином уже есть", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = MakeUser(login, email, pass);
        helper.InsertUserInBd(user);
        Navigation.findNavController(view).navigate(R.id.action_createAccFragment_to_loginScreenFragment);
    }

    private User MakeUser(String login, String email, String pass)
    {
        User user = new User();
        user.login = login;
        user.email = email;
        user.pass = pass;
        return user;
    }
}
