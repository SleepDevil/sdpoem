package com.example.sdpoem.ui.my;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private final MutableLiveData<Boolean> LoggedIn;

    public MyViewModel() {
        LoggedIn = new MutableLiveData<>();
        LoggedIn.setValue(false);
    }

    public LiveData<Boolean> getLoggedIn() {
        return LoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        LoggedIn.setValue(loggedIn);
    }
}