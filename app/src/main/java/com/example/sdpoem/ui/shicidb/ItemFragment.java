package com.example.sdpoem.ui.shicidb;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sdpoem.R;
import com.example.sdpoem.ui.shicidb.placeholder.PlaceholderContent;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {
    private ArrayList<PlaceholderContent.PlaceholderItem> PlaceholderItemList = new ArrayList();
    private RecyclerView recyclerView;
    String json = "";

    public ItemFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ShicidbFragment.MessageEvent event) {
        readData(event.ClickedName);
        Log.d("TAG", "onMessageEvent: " + event.ClickedName);
    }

    private void readData(String name) {
        PlaceholderItemList.clear();
        InputStream inputStream;
        // 获取数据
        try {
            Gson gson = new Gson();
            switch (name) {
                case "全唐诗":
                    inputStream = getContext().getResources().openRawResource(R.raw.tangshisanbai);
                    break;
                case "全宋诗":
                    Log.d("TAG", "readData: 进入咯");
                    inputStream = getContext().getResources().openRawResource(R.raw.songshi);
                    break;
                case "全宋词":
                    inputStream = getContext().getResources().openRawResource(R.raw.songcisanbai);
                    break;
                case "五代·花间集":
                    inputStream = getContext().getResources().openRawResource(R.raw.huajianji);
                    break;
                case "五代·南唐二主词":
                    inputStream = getContext().getResources().openRawResource(R.raw.nantangerzhu);
                    break;
                default:
                    inputStream = getContext().getResources().openRawResource(R.raw.tangshisanbai);
                    break;
            }
            Writer writer = new StringWriter();
            char[] buffer = new char[inputStream.available()];
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            json = writer.toString();
            JsonArray array = JsonParser.parseString(json).getAsJsonArray();
            for (JsonElement jelement : array) {
                PlaceholderContent.PlaceholderItem poemBean = gson.fromJson(jelement, PlaceholderContent.PlaceholderItem.class);
                PlaceholderItemList.add(poemBean);
            }
            MyItemRecyclerViewAdapter mAdapter = new MyItemRecyclerViewAdapter(PlaceholderItemList);

            recyclerView.setAdapter(mAdapter);
        } catch (Exception e) {
            Log.d("TAG", "MyItemRecyclerViewAdapter: errerer" + e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);// 注册eventbus
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        readData("全唐诗");
        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(PlaceholderItemList));
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);// 销毁eventbus
    }
}