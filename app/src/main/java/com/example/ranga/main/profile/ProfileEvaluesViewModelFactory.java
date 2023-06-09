package com.example.ranga.main.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ranga.database.User;
import com.example.ranga.main.comixInfo.ComixRatingViewModel;

public class ProfileEvaluesViewModelFactory implements ViewModelProvider.Factory
{
    private User user;

    public ProfileEvaluesViewModelFactory(User user)
    {
        this.user = user;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        if (modelClass.isAssignableFrom(ProfileEvaluesViewModel.class))
        {
            return (T) new ProfileEvaluesViewModel(user);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
