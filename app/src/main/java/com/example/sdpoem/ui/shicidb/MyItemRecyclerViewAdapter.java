package com.example.sdpoem.ui.shicidb;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        ViewHolder holder = new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getBindingAdapterPosition();
                PlaceholderItem placeholderItem = mValues.get(position);
                String deliverParagraphs = "";
                List<String> paragraphs = placeholderItem.paragraphs;
                String title;
                if (placeholderItem.title != null) {
                    title = placeholderItem.title;
                } else {
                    title = placeholderItem.rhythmic;
                }
                for (String sentence : paragraphs) {
                    deliverParagraphs += sentence;
                    deliverParagraphs += "\n";
                }
                String author = placeholderItem.author;

                Intent intent = new Intent(parent.getContext(), ShowPoemActivity.class);
                // 传递数据
                intent.putExtra("paragraphs", deliverParagraphs);
                intent.putExtra("title", title);
                intent.putExtra("author", author);
                parent.getContext().startActivity(intent);
            }

        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PlaceholderItem currentItem = mValues.get(position);
        if (currentItem.title != null) {// 唐诗为title，宋词为rhythmic
            holder.mTitleText.setText(currentItem.title);
        } else {
            holder.mTitleText.setText(currentItem.rhythmic);
        }
        if (currentItem.tags != null) {
            holder.mDescriptionText.setText(currentItem.tags.get(0));
        } else {
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