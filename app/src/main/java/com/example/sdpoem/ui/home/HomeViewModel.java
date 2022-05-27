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

    private final MutableLiveData<PoemResponse> poemResponse;
//    private final MutableLiveData<String> poemTitle;
//    private final MutableLiveData<String> poemAuthor;


    public Callback<PoemResponse> responseCallback = new Callback<PoemResponse>() {
        @Override
        public void onResponse(Call<PoemResponse> call, Response<PoemResponse> response) {
            PoemResponse res = response.body();
            Log.d("TAG", "onResponse: " + res);
            poemResponse.setValue(res);
//            poemTitle.setValue(res.title);
//            poemAuthor.setValue(res.author);
        }

        @Override
        public void onFailure(Call<PoemResponse> call, Throwable t) {
        }
    };

    public HomeViewModel() {
        poemResponse = new MutableLiveData<>();
//        poemTitle = new MutableLiveData<>();
//        poemAuthor = new MutableLiveData<>();
    }

    public LiveData<PoemResponse> getTotalText() {
        PoemService poemService = ServiceCreator.create(PoemService.class);
        poemService.getRandomPoem().enqueue(responseCallback);
        return poemResponse;
    }
}