package com.example.ranga.main.comixInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ranga.database.Comix;

public class ComixCommentsViewModelFactory implements ViewModelProvider.Factory
{
    private Comix comix;

    public ComixCommentsViewModelFactory(Comix comix)
    {
        this.comix = comix;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        if (modelClass.isAssignableFrom(ComixCommentsViewModel.class))
        {
            return (T) new ComixCommentsViewModel(comix);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
