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
    // 首页数据模型

    private final MutableLiveData<PoemResponse> poemResponse;
    // 随机推荐网络请求json数据格式


    public Callback<PoemResponse> responseCallback = new Callback<PoemResponse>() {
        @Override
        public void onResponse(Call<PoemResponse> call, Response<PoemResponse> response) {
            PoemResponse res = response.body();
            poemResponse.setValue(res);
        }

        @Override
        public void onFailure(Call<PoemResponse> call, Throwable t) {
        }
    };

    public HomeViewModel() {
        poemResponse = new MutableLiveData<>();
    }

    public LiveData<PoemResponse> getTotalText() {
        PoemService poemService = ServiceCreator.create(PoemService.class);
        poemService.getRandomPoem().enqueue(responseCallback);
        return poemResponse;
    }
}