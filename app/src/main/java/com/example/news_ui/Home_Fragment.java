package com.example.news_ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.news_ui.Recent_NewsRecycler.RecentAdapter;
import com.example.news_ui.Recent_NewsRecycler.Recent_helper;
import com.example.news_ui.Recommended_Slider.RecommendedAdapter;
import com.example.news_ui.TopAssets.TopDownloader;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Home_Fragment extends Fragment {

    final static String urlAddress = "https://timesofindia.indiatimes.com/rssfeeds/54829575.cms";

    // Context
    Context context;

    // Image view
    ImageView profile_image;
    
    
    // Recent Recycler View
    RecyclerView.Adapter Recent_adapter;
    RecyclerView Recent_recyclerView;

    // Images autoslider
    private SliderView sliderView;

    // Variables for the TopNews contents
    private int[] images;
    private String[] Title;
    private String[] Desc;


    // Recommeded AutoSlider
    private RecommendedAdapter R_sliderAdapter;
    private SliderView R_sliderView;

    // Variables for Recommended contents
    private int[] RecoImage;
    private String[] RecoTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Hooking the slider view
        sliderView = view.findViewById(R.id.AutoSlider);

        // Hooking the Recent recycler layout
        Recent_recyclerView = view.findViewById(R.id.Recent_recycler);

        // Hooking the Recommended slider layout
        R_sliderView = view.findViewById(R.id.Recommended_slider);


        // Setting up an TopNews SLiderAdapter
        new TopDownloader(context,sliderView,urlAddress);



        // Recommended Image array
        RecoImage = new int[]{
          R.drawable.sample_img,
          R.drawable.sample_img,
          R.drawable.sample_img,
          R.drawable.sample_img,
          R.drawable.sample_img,
        };

        // Recommended Tite array
        RecoTitle = new String[]{
                "STARTUPS",
                "POLITICS",
                "ENVIRONMENT",
                "EDUCATION",
                "WEATHER"
        };

        // Setting up an Recommended SliderAdapter


        // Method for Recent recycler
        RecentNews();

        // Setting up an Recommended sliderAdapter
        R_sliderAdapter = new RecommendedAdapter(RecoImage,RecoTitle);
        R_sliderView.setSliderAdapter(R_sliderAdapter);
        R_sliderView.setSliderTransformAnimation(SliderAnimations.HORIZONTALFLIPTRANSFORMATION);
        R_sliderView.setAutoCycle(true);

        profile_image = view.findViewById(R.id.Profile_image);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inten = new Intent(getContext(),Profile.class);
                startActivity(inten);
            }
        });



        return view;
    }



    private void RecentNews() {

        Recent_recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        Recent_recyclerView.setHasFixedSize(true);
        Recent_recyclerView.setNestedScrollingEnabled(false);

        ArrayList<Recent_helper> recentHelpers = new ArrayList<>();

        recentHelpers.add(new Recent_helper(R.drawable.sample_img,"ENVIRONMENT","Light pollution prevents us from seeing the stars at Night"));
        recentHelpers.add(new Recent_helper(R.drawable.sample_img,"POLITICS","PM. Modi told that congress cheated on 2G network in 2001"));
        recentHelpers.add(new Recent_helper(R.drawable.sample_img,"EDUCATION","Light pollution prevents us from seeing the stars at Night"));
        recentHelpers.add(new Recent_helper(R.drawable.sample_img,"ENVIRONMENT","PM. Modi told that congress cheated on 2G network in 2001"));
        recentHelpers.add(new Recent_helper(R.drawable.sample_img,"POLITICS","Light pollution prevents us from seeing the stars at Night"));
        recentHelpers.add(new Recent_helper(R.drawable.sample_img,"EDUCATION","PM. Modi told that congress cheated on 2G network in 2001"));

        Recent_adapter = new RecentAdapter(recentHelpers);
        Recent_recyclerView.setAdapter(Recent_adapter);

    }




}