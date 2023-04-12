package com.example.ranga.main.comixList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ranga.database.Comix;
import com.example.ranga.database.ComixTableQueriesHelper;

import java.util.List;

public class ComixListViewModel extends ViewModel
{
    private final LiveData<List<Comix>> comixListState = ComixTableQueriesHelper.GetAllComixLD();
    public LiveData<List<Comix>> getComixListState()
    {
        return comixListState;
    }
}
