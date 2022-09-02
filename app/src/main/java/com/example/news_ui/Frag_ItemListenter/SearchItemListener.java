package com.example.news_ui.Frag_ItemListenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_ui.R;
import com.example.news_ui.RSS_Assets.RSS_Parser;
import com.example.news_ui.Search_recycler.PiccasoClient;

public class SearchItemListener extends AppCompatActivity {

    ImageView ImageView;
    TextView titleText,desccText,dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_item_listener);

        ImageView = findViewById(R.id.SearchDeImage);
        titleText = findViewById(R.id.SearchDeTitle);
        desccText = findViewById(R.id.SearchDeDesc);
        dateText = findViewById(R.id.SearchDeDate);

        String title = getIntent().getExtras().getString("title");
        String descp = getIntent().getExtras().getString("description");
        String imageUrl = getIntent().getExtras().getString("image");
        String Date = getIntent().getExtras().getString("date");

        titleText.setText(title);
        desccText.setText(descp);
        PiccasoClient.DownloadImage(getApplicationContext(),imageUrl,ImageView);
        dateText.setText(Date);



    }
}