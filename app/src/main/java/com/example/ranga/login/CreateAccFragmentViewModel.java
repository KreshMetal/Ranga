package com.example.ranga.login;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.ranga.App;
import com.example.ranga.EditTextFragmentViewModel;
import com.example.ranga.R;
import com.example.ranga.database.User;

import java.util.Arrays;

public class CreateAccFragmentViewModel extends EditTextFragmentViewModel
{
    private final UsersTableQueriesHelper helper;

    public CreateAccFragmentViewModel()
    {
        helper = new UsersTableQueriesHelper(App.getInstance().getDatabase().userDao());
        readys.postValue(new boolean[] {false, false, false, false, false});
    }

    private boolean CheckLoginOnBd(String login)
    {
        User user = helper.GetUserFromBd(login);
        return user != null;
    }

    public void OnCreateAccBtnWasClicked(View view, String login, String email, String pass, int avatar)
    {
        if (CheckLoginOnBd(login))
        {
            Toast.makeText(view.getContext(), "Аккаунт с таким логином уже есть", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = MakeUser(login, email, pass, avatar);
        helper.InsertUserInBd(user);
        Navigation.findNavController(view).navigate(R.id.action_createAccFragment_to_loginScreenFragment);
    }

    private User MakeUser(String login, String email, String pass, int avatar)
    {
        User user = new User();
        user.login = login;
        user.email = email;
        user.pass = pass;
        user.avatar = avatar;
        return user;
    }

}
