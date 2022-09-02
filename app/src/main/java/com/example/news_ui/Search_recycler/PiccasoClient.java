package com.example.news_ui.Search_recycler;

import android.content.Context;
import android.widget.ImageView;

import com.example.news_ui.R;
import com.squareup.picasso.Picasso;

public class PiccasoClient {

    public static void DownloadImage(Context c, String ImageUrl, ImageView imgs){
        if (ImageUrl != null && ImageUrl.length()>0){
            Picasso.get().load(ImageUrl).placeholder(R.drawable.ic_launcher_foreground).into(imgs);
        }else {
            Picasso.get().load(R.drawable.ic_launcher_foreground).into(imgs);
        }

    }
}
