package com.example.ranga.database;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.ranga.App;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvaluationTableQuariesHelper
{
    private static final EvalutationDao dao = App.getInstance().getDatabase().evalutationDao();

    public static void InsertInBd(Evaluation evaluation)
    {
        InsertEvaluationTask task = new InsertEvaluationTask(evaluation);
        task.execute();
    }

    public static void Update(@NonNull Evaluation evaluation)
    {
        UpdateEvaluationsTask task = new UpdateEvaluationsTask(evaluation);
        task.execute();
    }

    public static LiveData<List<Evaluation>> GetAllEvaluationForComix(Comix comix)
    {
        GetAllEvaluationForComixTask task = new GetAllEvaluationForComixTask(comix.id);
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

    public static LiveData<List<Evaluation>> GetAllEvaluationForUser(long userId)
    {
        GetAllEvaluationsForUserTask task = new GetAllEvaluationsForUserTask(userId);
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

    public static Evaluation GetByComixIdForUser(long comixId, long userId)
    {
        GetByComixIdForUserTask task = new GetByComixIdForUserTask(comixId, userId);
        task.execute();
        try {
            return task.get();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private static class InsertEvaluationTask extends AsyncTask<Void, Void, Void>
    {
        private final Evaluation evaluation;

        public InsertEvaluationTask(Evaluation evaluation)
        {
            this.evaluation = evaluation;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            dao.insert(evaluation);
            return null;
        }
    }

    private static class GetAllEvaluationForComixTask extends AsyncTask<Void, Void, LiveData<List<Evaluation>>>
    {
        private final long comixId;

        public GetAllEvaluationForComixTask(long id)
        {
            comixId = id;
        }

        protected LiveData<List<Evaluation>> doInBackground(Void... voids)
        {
            return dao.getByIdComix(comixId);
        }
    }

    private static class UpdateEvaluationsTask extends AsyncTask<Void, Void, Void>
    {
        private final Evaluation evaluation;

        public  UpdateEvaluationsTask(Evaluation evaluation){
            this.evaluation = evaluation;
        }


        @Override
        protected Void doInBackground(Void... voids)
        {
            dao.update(evaluation);
            return null;
        }
    }

    private static class GetByComixIdForUserTask extends AsyncTask<Void, Void, Evaluation>
    {
        private final long comixId;
        private final long userId;

        GetByComixIdForUserTask(long comixId, long userId)
        {
            this.comixId = comixId;
            this.userId = userId;
        }

        @Override
        protected Evaluation doInBackground(Void... voids)
        {
            return dao.getByIdComixForUser(comixId, userId);
        }
    }

    private static class GetAllEvaluationsForUserTask extends AsyncTask<Void, Void, LiveData<List<Evaluation>>>
    {
        private final long userId;

        public GetAllEvaluationsForUserTask(long userId)
        {
            this.userId = userId;
        }


        @Override
        protected LiveData<List<Evaluation>> doInBackground(Void... voids)
        {
            return dao.getAllUserEvaluations(userId);
        }
    }
}
