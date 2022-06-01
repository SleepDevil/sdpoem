package com.example.sdpoem.ui.write;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sdpoem.R;

import java.util.List;

public class PoemAdapter extends RecyclerView.Adapter<PoemAdapter.ViewHolder> {
    private List<Poem> lcoalDataList;

    public PoemAdapter(List<Poem> poemList) {
        lcoalDataList = poemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.writeen_poem_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTitleText().setText(lcoalDataList.get(position).Title);
        holder.getContentText().setText(lcoalDataList.get(position).Content);
        holder.getWrittenTimeText().setText(lcoalDataList.get(position).WrittenTime);
    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCount: "+lcoalDataList.size());
        return lcoalDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView getTitleText() {
            return titleText;
        }

        public TextView getContentText() {
            return contentText;
        }

        public TextView getWrittenTimeText() {
            return writtenTimeText;
        }

        private TextView titleText;
        private TextView contentText;
        private TextView writtenTimeText;

        public ViewHolder(View view) {
            super(view);
            titleText = (TextView) view.findViewById(R.id.title);
            contentText = (TextView) view.findViewById(R.id.content);
            writtenTimeText = (TextView) view.findViewById(R.id.written_time);
        }
    }
}
