package com.example.ranga.database;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.ranga.App;

import java.util.List;

public class ComixTableQueriesHelper
{
    private static final ComixDao dao = App.getInstance().getDatabase().comixDao();

    public static void InsertComixInBd(Comix comix)
    {
        InsertComixTask insertComixTask = new InsertComixTask(comix);
        insertComixTask.execute();
    }

    public static LiveData<List<Comix>> GetAllComixLD()
    {
        GetAllComixLDTask task = new GetAllComixLDTask();
        task.execute();
        try
        {
            return task.get();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private static class InsertComixTask extends AsyncTask<Void, Void, Void>
    {
        private final Comix comix;

        public InsertComixTask(Comix comix)
        {
            this.comix = comix;
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            dao.insert(comix);
            return null;
        }
    }

    private static class GetAllComixLDTask extends AsyncTask<Void, Void, LiveData<List<Comix>>>
    {

        @Override
        protected LiveData<List<Comix>> doInBackground(Void... voids)
        {
            return dao.getAllLD();
        }
    }
}
