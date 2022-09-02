package com.example.news_ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news_ui.RSS_assetsCricket.CrickDownloader;
import com.example.news_ui.Saved_recycler.SavedAdapter;
import com.example.news_ui.Saved_recycler.Saved_helperClass;

import java.util.ArrayList;


public class Save_Fragment extends Fragment {

    Context context;

    final static String urlAddress = "https://timesofindia.indiatimes.com/rssfeeds/54829575.cms";

    // Saved RecyclerView
    RecyclerView.Adapter Saved_adapters;
    //RecyclerView Saved_RecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.save_fragment, container, false);

        // saved recyclerView
        final RecyclerView Saved_RecyclerView = view.findViewById(R.id.saved_recyclerView);
        Saved_RecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        new CrickDownloader(getContext(),Saved_RecyclerView,urlAddress).execute();

        return view;
    }


}