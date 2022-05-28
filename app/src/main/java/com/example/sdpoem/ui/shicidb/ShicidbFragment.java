package com.example.sdpoem.ui.shicidb;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sdpoem.R;
import com.example.sdpoem.databinding.FragmentShicidbBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class ShicidbFragment extends Fragment {

    public static class MessageEvent {
        public MessageEvent(String clickedName) {
            ClickedName = clickedName;
        }

        public String ClickedName;
    }

    private FragmentShicidbBinding binding;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        // Do something
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);// 注册eventbus
        ShicidbViewModel shicidbViewModel =
                new ViewModelProvider(this).get(ShicidbViewModel.class);

        binding = FragmentShicidbBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        NavigationView navView = root.findViewById(R.id.leftNav);
        navView.getMenu().getItem(0).setChecked(true);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.d("TAG", "onNavigationItemSelected: " + menuItem.getTitle());
                EventBus.getDefault().post(new MessageEvent((String) menuItem.getTitle()));
                return true;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        EventBus.getDefault().unregister(this);// 销毁eventbus
    }
}