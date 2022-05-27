package com.example.sdpoem.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sdpoem.logic.model.PoemResponse;
import com.example.sdpoem.logic.network.PoemService;
import com.example.sdpoem.logic.network.ServiceCreator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Callback<PoemResponse> responseCallback = new Callback<PoemResponse>() {
        @Override
        public void onResponse(Call<PoemResponse> call, Response<PoemResponse> response) {
            mText.setValue(response.body().getContent());
        }

        @Override
        public void onFailure(Call<PoemResponse> call, Throwable t) {
        }
    };

    public HomeViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        PoemService poemService = ServiceCreator.create(PoemService.class);
        poemService.getRandomPoem().enqueue(responseCallback);
        return mText;
    }

    public void setText(String toSetVal) {
        mText.setValue(toSetVal);
    }
}