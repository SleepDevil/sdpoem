package com.example.sdpoem.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sdpoem.R;
import com.example.sdpoem.databinding.FragmentHomeBinding;
import com.example.sdpoem.logic.model.PoemResponse;
import com.example.sdpoem.logic.network.PoemService;
import com.example.sdpoem.logic.network.RandomPoem;
import com.example.sdpoem.logic.network.ServiceCreator;
import com.youth.banner.Banner;
import com.youth.banner.indicator.BaseIndicator;
import com.youth.banner.indicator.RoundLinesIndicator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView poemContent = binding.randomPoemContent;
        final TextView poemTitle = binding.randomPoemTitle;
        final TextView poemAuthor = binding.randomPoemAuthor;

        final Observer<PoemResponse> nameObserver = new Observer<PoemResponse>() {
            @Override
            public void onChanged(PoemResponse poemResponse) {
                poemContent.setText(poemResponse.content);
                poemTitle.setText(poemResponse.title);
                poemAuthor.setText(poemResponse.author);
            }
        };

        homeViewModel.getTotalText().observe(getViewLifecycleOwner(), nameObserver);

        SwipeRefreshLayout swipeRefreshPoem = root.findViewById(R.id.swiperefresh);
        swipeRefreshPoem.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("LOG_TAG", "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        PoemService poemService = ServiceCreator.create(PoemService.class);
                        poemService.getRandomPoem().enqueue(homeViewModel.responseCallback);// 重新获取数据
                        swipeRefreshPoem.setRefreshing(false);// 取消刷新动画
                    }
                }
        );
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Banner banner = getView().findViewById(R.id.banner);
        banner.setAdapter(new ImageAdapter(DataBean.getTestData()));
        banner.setIndicator(new RoundLinesIndicator(getContext()));

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}