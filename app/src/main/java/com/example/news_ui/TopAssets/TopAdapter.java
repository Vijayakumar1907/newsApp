package com.example.news_ui.TopAssets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_ui.R;
import com.example.news_ui.Search_recycler.PiccasoClient;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class TopAdapter extends SliderViewAdapter<TopAdapter.SliderAdapterViewHolder> {

    private final List<TopHelper> topHelpers;
    Context context;
    public TopAdapter(Context context,List<TopHelper> topHelpers) {
        this.topHelpers = topHelpers;
        this.context = context;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.auto_imageslider_layout, null);
        return new SliderAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {

        final TopHelper helper = topHelpers.get(position);

        String title = helper.getTitle();
        String Date = helper.getDate();
        String ImgUrl = helper.getImageUrl().replace("timesofindia.indiatimes.com","timesofindia.indiatimes.com");
        PiccasoClient.DownloadImage(context,ImgUrl,viewHolder.imageView);

        viewHolder.TitleTop.setText(title);
        viewHolder.DateTop.setText(Date);



    }

    @Override
    public int getCount() {
        return topHelpers.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder{

        ImageView imageView;
        TextView DateTop,TitleTop;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ImageViewSlider);
            DateTop = itemView.findViewById(R.id.TitleDate);
            TitleTop = itemView.findViewById(R.id.News_Title);
        }
    }
}

