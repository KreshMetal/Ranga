package com.example.ranga.login;

import android.os.AsyncTask;

import com.example.ranga.database.User;
import com.example.ranga.database.UserDao;

import java.util.List;

public class UsersTableQueriesHelper
{
    private final UserDao dao;

    public UsersTableQueriesHelper(UserDao dao)
    {
        this.dao = dao;
    }

    public User GetUserFromBd(String login)
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

    public List<User> GetUsersFromBd()
    {
        GetAllUsersTask task = new GetAllUsersTask();
        task.execute();
        try
        {
            List<User> users = task.get();
            return users;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void InsertUserInBd(User user)
    {
        InsertUserTask task = new InsertUserTask(user);
        task.execute();
    }

    private class GetUserTask extends AsyncTask<Void, Void, User>
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

    private class GetAllUsersTask extends AsyncTask<Void, Void, List<User>>
    {
        @Override
        protected List<User> doInBackground(Void... voids) {
            return dao.getAll();
        }
    }

    private class InsertUserTask extends AsyncTask<Void, Void, Void>
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
}
