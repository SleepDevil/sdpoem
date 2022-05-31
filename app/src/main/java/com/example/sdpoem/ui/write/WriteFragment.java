package com.example.sdpoem.ui.write;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.sdpoem.databinding.FragmentWriteBinding;
import com.example.sdpoem.ui.my.LoginActivity;

import java.util.List;

public class WriteFragment extends Fragment {
    private List<Poem> PoemList;
    private FragmentWriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //此处是跳转的result回调方法
            }
        });

        MyDatabase db = Room.databaseBuilder(getContext(),
                MyDatabase.class, "written_poem").build();
        PoemDao poemDao = db.poemDao();


        WriteViewModel writeViewModel =
                new ViewModelProvider(this).get(WriteViewModel.class);

        binding = FragmentWriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WritePoemActivity.class);
                intentActivityResultLauncher.launch(intent);
            }
        });
        RecyclerView poemRecy = binding.writtemPoemsRecyclerview;
        new Thread(() -> {
            PoemList = poemDao.getPoemList();
            getActivity().runOnUiThread(() -> {
                poemRecy.setAdapter(new PoemAdapter(PoemList));
                poemRecy.setLayoutManager(new LinearLayoutManager(getContext()));
            });
        }).start();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}