package com.example.news_ui;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news_ui.RSS_Assets.Downloader;
import com.example.news_ui.Search_recycler.SearchAdapter;
import com.example.news_ui.Search_recycler.Search_helper;

import java.util.ArrayList;


public class Search_Fragment extends Fragment {

    final static String urlAddress = "https://timesofindia.indiatimes.com/rssfeeds/2950623.cms";

    SearchView searchView;
    SearchAdapter adapter;
    // Search recycler view
    RecyclerView.Adapter Search_adapter;

    Context cns;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        searchView = view.findViewById(R.id.SearchView);
        searchView.setQueryHint("Search here...");
        searchView.setIconifiedByDefault(false);

        // Hooking the search recycler
        final RecyclerView SearchRecycler = view.findViewById(R.id.Search_recyclerView);
        SearchRecycler.setLayoutManager(new LinearLayoutManager(cns,LinearLayoutManager.VERTICAL,false));
        new Downloader(getContext(),SearchRecycler,urlAddress).execute();

       // SearchRecycler.setAdapter(adapter);
        return view;
    }


}