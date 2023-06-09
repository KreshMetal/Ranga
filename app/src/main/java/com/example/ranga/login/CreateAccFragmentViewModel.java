package com.example.ranga.login;

import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.ranga.EditTextFragmentViewModel;
import com.example.ranga.R;
import com.example.ranga.database.User;
import com.example.ranga.database.UsersTableQueriesHelper;

public class CreateAccFragmentViewModel extends EditTextFragmentViewModel
{

    public CreateAccFragmentViewModel()
    {
        readys.postValue(new boolean[] {false, false, false, false, false});
    }

    private boolean CheckLoginOnBd(String login)
    {
        User user = UsersTableQueriesHelper.GetUserFromBd(login);
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
        UsersTableQueriesHelper.InsertUserInBd(user);
        Navigation.findNavController(view).navigate(R.id.action_createAccFragment_to_loginScreenFragment);
    }

    private User MakeUser(String login, String email, String pass, int avatar)
    {
        User user = new User();
        user.login = login;
        user.email = email;
        user.pass = pass;
        user.avatar = avatar;
        user.status = "";
        return user;
    }

}
