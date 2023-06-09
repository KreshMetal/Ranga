package com.example.ranga.main.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ranga.database.Comix;
import com.example.ranga.database.Evaluation;
import com.example.ranga.database.EvaluationTableQuariesHelper;
import com.example.ranga.database.User;

import java.util.List;

public class ProfileEvaluesViewModel extends ViewModel
{
    private final User user;

    public LiveData<List<Evaluation>> getEvalutations() {
        return evalutations;
    }

    private LiveData<List<Evaluation>> evalutations;

    public ProfileEvaluesViewModel(User user)
    {
        this.user = user;
        evalutations = EvaluationTableQuariesHelper.GetAllEvaluationForUser(user.id);
    }
}
