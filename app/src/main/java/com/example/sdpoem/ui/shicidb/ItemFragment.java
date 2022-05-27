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

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        // 获取数据
        ArrayList<PlaceholderContent.PlaceholderItem> PlaceholderItemList = new ArrayList();
        String json = "";
        try {
            Gson gson = new Gson();
            InputStream inputStream = getContext().getResources().openRawResource(R.raw.songcisanbai);
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
        } catch (Exception e) {
            Log.d("TAG", "MyItemRecyclerViewAdapter: errerer" + e);
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(PlaceholderItemList));
        }
        return view;
    }
}