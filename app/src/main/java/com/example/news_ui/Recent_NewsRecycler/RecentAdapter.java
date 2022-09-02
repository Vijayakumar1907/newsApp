package com.example.news_ui.Recent_NewsRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_ui.R;

import java.util.ArrayList;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentViewHolder> {

        ArrayList<Recent_helper> recent_helpers;

    public RecentAdapter(ArrayList<Recent_helper> recent_helpers) {
        this.recent_helpers = recent_helpers;
    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_news_layout,parent,false);
        RecentViewHolder viewHolder = new RecentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int position) {
        Recent_helper helper = recent_helpers.get(position);


        holder.recentImage.setImageResource(helper.getRecentImage());
        holder.recentTitle.setText(helper.getRecentTitle());
        holder.recentDesc.setText(helper.getRecentDesc());

    }

    @Override
    public int getItemCount() {
        return recent_helpers.size();
    }

    public static class RecentViewHolder extends RecyclerView.ViewHolder{

        ImageView recentImage;
        TextView recentTitle,recentDesc;

        public RecentViewHolder(@NonNull View itemView) {
            super(itemView);

            recentImage = itemView.findViewById(R.id.Recent_Image);
            recentTitle = itemView.findViewById(R.id.Recent_title);
            recentDesc = itemView.findViewById(R.id.Recent_Desc);


        }

    }
}
