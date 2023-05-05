package com.example.ranga;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;

abstract public class EditTextFragmentViewModel extends ViewModel
{
    public MutableLiveData<boolean[]> getReadys() {
        return readys;
    }

    protected MutableLiveData<boolean[]> readys = new MutableLiveData<>();

    public void SetReadyStatus(int editTextNumber, boolean value)
    {
        boolean[] old = readys.getValue();
        boolean[] updated = Arrays.copyOf(old, old.length);
        updated[editTextNumber] = value;
        readys.postValue(updated);
    }
}
