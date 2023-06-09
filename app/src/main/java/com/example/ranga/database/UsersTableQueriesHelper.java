package com.example.ranga.database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ranga.App;
import com.example.ranga.database.User;
import com.example.ranga.database.UserDao;

import java.util.List;

public class UsersTableQueriesHelper
{
    private static final UserDao dao = App.getInstance().getDatabase().userDao();

    public static User GetUserFromBd(String login)
    {
        GetUserTask task = new GetUserTask(login);
        task.execute();
        try
        {
            User user = task.get();
            return user;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static void InsertUserInBd(User user)
    {
        InsertUserTask task = new InsertUserTask(user);
        task.execute();
    }

    public static void UpdateUser(User user)
    {
        UpdateUserTask task = new UpdateUserTask(user);
        task.execute();
    }

    private static class GetUserTask extends AsyncTask<Void, Void, User>
    {
        private final String login;

        public GetUserTask(String login)
        {
            this.login = login;
        }

        @Override
        protected User doInBackground(Void... voids) {
            return dao.getByLogin(login);
        }
    }

    private static class InsertUserTask extends AsyncTask<Void, Void, Void>
    {
        private final User user;

        public InsertUserTask(User user)
        {
            this.user = user;
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            dao.insert(user);
            return null;
        }
    }

    private static class UpdateUserTask extends AsyncTask<Void, Void, Void>
    {
        private final User user;

        public UpdateUserTask(User user)
        {
            this.user = user;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.update(user);
            Log.d("TEST", "Updated");
            return null;
        }
    }
}
