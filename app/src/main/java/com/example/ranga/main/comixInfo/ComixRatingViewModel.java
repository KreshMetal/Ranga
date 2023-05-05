package com.example.ranga.main.comixInfo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ranga.App;
import com.example.ranga.database.Comix;
import com.example.ranga.database.Evaluation;
import com.example.ranga.database.EvaluationTableQuariesHelper;

import java.text.DecimalFormat;
import java.util.List;

public class ComixRatingViewModel extends ViewModel
{
    private final Comix comix;
    private final LiveData<List<Evaluation>> evalutations;
    private Evaluation userEvaluation = null;

    public ComixRatingViewModel(Comix comix)
    {
        this.comix = comix;
        evalutations = EvaluationTableQuariesHelper.GetAllEvaluationForComix(comix);
    }

    public long GetUserEvaluation()
    {
        userEvaluation = EvaluationTableQuariesHelper.GetByComixIdForUser(comix.id, App.getInstance().getUser().id);
        return userEvaluation != null ? userEvaluation.rating : 0;
    }
    public LiveData<List<Evaluation>> getEvalutations()
    {
        return evalutations;
    }

    public Rating GetViewsData()
    {
        List<Evaluation> evas = evalutations.getValue();
        int[] counts = {0,0,0,0,0};
        int total = 0;
        for (Evaluation eva:evas)
        {
            counts[(int)(eva.rating - 1)] += 1;
            total += eva.rating;
        }
        String rating = "0";
        int[] pbs = {0,0,0,0,0};
        if (total != 0)
        {
            rating = new DecimalFormat("#0.0").format((float)total / evas.size());
            for (int i = 0; i < 5; i++)
            {
                int tmp = (int)(((float)counts[i] / evas.size()) * 100);
                pbs[i] = tmp;
            }
        }
        return new Rating(pbs, evas.size(), rating);
    }

    public void OnStarClicked(int star)
    {
        if (userEvaluation == null)
        {
            Evaluation evaluation = new Evaluation();
            evaluation.user = App.getInstance().getUser();
            evaluation.comix = comix;
            evaluation.rating = star;
            EvaluationTableQuariesHelper.InsertInBd(evaluation);
            GetUserEvaluation();
        }
        else
        {
            userEvaluation.rating = star;
            Log.d("TEST", userEvaluation.id + "");
            EvaluationTableQuariesHelper.Update(userEvaluation);
        }
    }

    class Rating
    {
        public final int[] pbs;
        public final int voiceCount;
        public final String rating;

        public Rating(int[] pbs, int voiceCount, String rating)
        {
            this.pbs = pbs;
            this.voiceCount = voiceCount;
            this.rating = rating;
        }
    }
}
