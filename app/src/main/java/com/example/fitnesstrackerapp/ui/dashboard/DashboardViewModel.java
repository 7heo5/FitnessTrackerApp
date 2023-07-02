package com.example.fitnesstrackerapp.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    //Enables the text to be displayed for demo purposes
    public void SetText(String text)
    {
        mText.setValue(text);
    }

    public LiveData<String> getText() {
        return mText;
    }
}