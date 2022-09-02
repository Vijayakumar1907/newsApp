package com.example.news_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {

    // Initializing the arrays
    private int images [];
    private String Title [];
    private String Desc [];

    public SliderAdapter(){

    }

    public SliderAdapter(int[] images, String[] title, String[] desc) {
        this.images = images;
        this.Title = title;
        this.Desc = desc;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.auto_imageslider_layout,null);
        SliderViewHolder sliderViewHolder = new SliderViewHolder(view);
        return sliderViewHolder;
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {

        viewHolder.SliderImage.setImageResource(images[position]);
        viewHolder.titleText.setText(Title[position]);
        viewHolder.DescText.setText(Desc[position]);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder{

        private ImageView SliderImage;
        private TextView titleText,DescText;

        public SliderViewHolder(View itemView) {
            super(itemView);

            SliderImage = itemView.findViewById(R.id.ImageViewSlider);
            titleText = itemView.findViewById(R.id.News_Title);
            DescText = itemView.findViewById(R.id.Title_description);
        }
    }

}
