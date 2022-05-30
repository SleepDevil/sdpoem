package com.example.sdpoem.ui.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.sdpoem.R;
import com.example.sdpoem.databinding.FragmentMyBinding;

public class MyFragment extends Fragment {

    private FragmentMyBinding binding;
    private SharedPreferences.Editor editor;
    private SharedPreferences UserPref;
    Boolean LoggedIn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //此处是跳转的result回调方法
                if (result.getData() != null && result.getResultCode() == Activity.RESULT_OK) {
                    Log.d("TAG", "onActivityResult: " + result.getData().getStringExtra("changedata"));
                    
                } else {
//                                Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
                }
            }
        });
        MyViewModel notificationsViewModel =
                new ViewModelProvider(this).get(MyViewModel.class);

        binding = FragmentMyBinding.inflate(inflater, container, false);

        Glide.with(getContext()).load(R.drawable.user) //图片地址
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(binding.userImg);
        UserPref = getActivity().getSharedPreferences("userPref", Context.MODE_PRIVATE);
        editor = UserPref.edit();
        LoggedIn = UserPref.getBoolean("loggedin", false);
        binding.username.setText(LoggedIn ? UserPref.getString("username", "用户名") : "用户名");
        Log.d("TAG", "onCreateView: " + LoggedIn);
        binding.bottmBtn.setText(LoggedIn ? "退出登录" : "立即登录");
        binding.bottmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoggedIn) {
                    // 此时为登录状态，进行退出登录逻辑
                    editor.putBoolean("loggedin", false).apply();
                    binding.bottmBtn.setText("立即登录");
                    Toast.makeText(getContext(), "退出成功！", Toast.LENGTH_SHORT).show();
                } else {
                    // 跳转到登录页面
//                    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//                        @Override
//                        public void onActivityResult(ActivityResult result) {
//                            //此处是跳转的result回调方法
//                            if (result.getData() != null && result.getResultCode() == Activity.RESULT_OK) {
//                                result.getData().getStringExtra("changedata");
//                            } else {
////                                Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intentActivityResultLauncher.launch(intent);
                }
            }
        });

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}