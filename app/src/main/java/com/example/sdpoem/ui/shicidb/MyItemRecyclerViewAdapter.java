package com.example.sdpoem.ui.shicidb;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sdpoem.databinding.FragmentItemBinding;
import com.example.sdpoem.ui.shicidb.placeholder.PlaceholderContent.PlaceholderItem;

import java.util.ArrayList;
import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private Context context;

    private ArrayList<PlaceholderItem> mValues;

    public MyItemRecyclerViewAdapter(ArrayList<PlaceholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PlaceholderItem currentItem = mValues.get(position);
//        holder.mItem = mValues.get(position);
        if (currentItem.title != null) {// 唐诗为title，宋词为rhythmic
            holder.mTitleText.setText(currentItem.title);
        } else {
            holder.mTitleText.setText(currentItem.rhythmic);
        }
        if (currentItem.tags != null) {
            holder.mDescriptionText.setText(currentItem.tags.get(0));
        }else {
            // 宋诗无tag，动态删除descript text TextView
            ((ViewGroup) holder.mDescriptionText.getParent()).removeView(holder.mDescriptionText);
        }
        holder.mAuthorText.setText(currentItem.author);
    }

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 2;
        }
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitleText;
        public final TextView mDescriptionText;
        public final TextView mAuthorText;
        public PlaceholderItem mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mTitleText = binding.title;
            mDescriptionText = binding.description;
            mAuthorText = binding.author;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescriptionText.getText() + "'";
        }
    }
}