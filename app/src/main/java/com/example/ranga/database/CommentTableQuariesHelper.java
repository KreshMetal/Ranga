package com.example.ranga.database;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.ranga.App;

import java.util.List;

public class CommentTableQuariesHelper
{
    private static CommentDao dao = App.getInstance().getDatabase().commentDao();

    public static LiveData<List<Comment>> GetByIdComix(long comixId)
    {
        GetByIdComixTask task = new GetByIdComixTask(comixId);
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

    public static void AddComment(Comment comment)
    {
        AddCommendTask task = new AddCommendTask(comment);
        task.execute();
    }

    private static class GetByIdComixTask extends AsyncTask<Void, Void, LiveData<List<Comment>>>
    {
        private final long id;

        public GetByIdComixTask(long id)
        {
            this.id = id;
        }


        @Override
        protected LiveData<List<Comment>> doInBackground(Void... voids)
        {
            return dao.getByIdComix(id);
        }
    }

    private static class AddCommendTask extends AsyncTask<Void, Void, Void>
    {
        private final Comment comment;

        public AddCommendTask(Comment comment)
        {
            this.comment = comment;
        }


        @Override
        protected Void doInBackground(Void... voids)
        {
            dao.insert(comment);
            return null;
        }
    }
}
