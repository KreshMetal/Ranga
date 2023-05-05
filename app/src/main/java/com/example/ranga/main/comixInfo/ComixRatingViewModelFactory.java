package com.example.ranga.main.comixInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ranga.database.Comix;

public class ComixRatingViewModelFactory implements ViewModelProvider.Factory
{
    private Comix comix;

    public ComixRatingViewModelFactory(Comix comix)
    {
        this.comix = comix;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        if (modelClass.isAssignableFrom(ComixRatingViewModel.class))
        {
            return (T) new ComixRatingViewModel(comix);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
