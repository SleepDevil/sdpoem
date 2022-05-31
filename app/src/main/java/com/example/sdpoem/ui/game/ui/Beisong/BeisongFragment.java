package com.example.sdpoem.ui.game.ui.Beisong;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sdpoem.R;
import com.example.sdpoem.databinding.ActivityBeisongBinding;
import com.example.sdpoem.databinding.FragmentGameBinding;
import com.example.sdpoem.ui.shicidb.placeholder.PlaceholderContent;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class BeisongFragment extends Fragment {
    private ActivityBeisongBinding binding;

    private BeisongViewModel mViewModel;
    private ArrayList<String> paragraphs = new ArrayList<>();
    private ArrayList<TextView> AnsTextViewList = new ArrayList<>();


    public static BeisongFragment newInstance() {
        return new BeisongFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ActivityBeisongBinding.inflate(inflater, container, false);
        readData();
        String ans;
        Random random = new Random();
        int pos = random.nextInt(paragraphs.size());
        if (pos % 2 == 0) {
            ans = paragraphs.get(pos + 1);
            // 偶數，为第一句，后一句为下划线
            binding.topQuestion.setText(paragraphs.get(pos) + "，\n_______");
        } else {
            ans = paragraphs.get(pos - 1);
            binding.topQuestion.setText("_______，\n" + paragraphs.get(pos));
        }
        AnsTextViewList.add(binding.ans1);
        AnsTextViewList.add(binding.ans2);
        AnsTextViewList.add(binding.ans3);
        AnsTextViewList.add(binding.ans4);
        int randomAnsPos = random.nextInt(AnsTextViewList.size());
        for (int i = 0; i < AnsTextViewList.size(); i++) {
            int finalI = i;
            AnsTextViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == randomAnsPos) {
                        view.setBackgroundColor(Color.rgb(91, 255, 29));

                    } else {
                        view.setBackgroundColor(Color.rgb(240, 1, 2));
                    }
                }
            });
            if (i == randomAnsPos) {
                AnsTextViewList.get(i).setText(ans);
                continue;
            }
            AnsTextViewList.get(i).setText(paragraphs.get(random.nextInt(paragraphs.size())));
        }

//        return inflater.inflate(R.layout.activity_beisong, container, false);
        return binding.getRoot();
    }


    private void readData() {
        // 获取数据
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.tangshisanbai);

            Gson gson = new Gson();
            Writer writer = new StringWriter();
            char[] buffer = new char[inputStream.available()];
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            String json = writer.toString();
            JsonArray array = JsonParser.parseString(json).getAsJsonArray();
            for (JsonElement jelement : array) {
                PlaceholderContent.PlaceholderItem poemBean = gson.fromJson(jelement, PlaceholderContent.PlaceholderItem.class);
                for (String p : poemBean.paragraphs) {
                    if (p.split("，").length != 2) {
                        // 去除不规则诗词
                        continue;
                    }
                    paragraphs.add(p.split("，")[0]);
                    paragraphs.add(p.split("，")[1]);
                }
            }
            Log.d("TAG", "readData: " + paragraphs);
        } catch (Exception e) {
            Log.d("TAG", "MyItemRecyclerViewAdapter: errerer" + e);
        }
    }

}