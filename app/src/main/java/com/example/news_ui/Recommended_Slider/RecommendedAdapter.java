package com.example.news_ui.Recommended_Slider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_ui.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class RecommendedAdapter extends SliderViewAdapter<RecommendedAdapter.RecommendedViewHolder> {

    // Initializing the variables
    private int R_images[];
    private String R_title[];

    public RecommendedAdapter(){

    }

    public RecommendedAdapter(int[] r_images, String[] r_title) {
        this.R_images = r_images;
        this.R_title = r_title;
    }

    @Override
    public RecommendedViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_layout,parent,false);
        RecommendedViewHolder recommendedViewHolder = new RecommendedViewHolder(view);
        return recommendedViewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendedViewHolder viewHolder, int position) {

        viewHolder.RecommendedImage.setImageResource(R_images[position]);
        viewHolder.RecommendedTitle.setText(R_title[position]);

    }

    @Override
    public int getCount() {
        return R_images.length;
    }

    public static class RecommendedViewHolder extends SliderViewAdapter.ViewHolder{

        ImageView RecommendedImage;
        TextView RecommendedTitle;

        public RecommendedViewHolder(View itemView) {
            super(itemView);
            RecommendedImage = itemView.findViewById(R.id.recommendedImage);
            RecommendedTitle = itemView.findViewById(R.id.recommendedTitle);
        }
    }
}
