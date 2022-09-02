package com.example.news_ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;


public class Categories_Fragment extends Fragment {

    Context context;

    Button Recent_btn,
            Top_btn,
            Recommend_btn,
            Politics_btn,
            Education_btn,
            Environment_btn,
            Corona_btn,
            Cinema_btn,
            Farming_btn,
            Industries_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.categories_fragment, container, false);

        Category_btnHooks(view);

        Recent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Recent News", Toast.LENGTH_SHORT).show();
            }
        });
        Top_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Top News", Toast.LENGTH_SHORT).show();
            }
        });
        Recommend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Recommended News", Toast.LENGTH_SHORT).show();
            }
        });
        Politics_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Politics News", Toast.LENGTH_SHORT).show();
            }
        });
        Education_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Education News", Toast.LENGTH_SHORT).show();
            }
        });
        Environment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Environment News", Toast.LENGTH_SHORT).show();
            }
        });
        Corona_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Corona News", Toast.LENGTH_SHORT).show();
            }
        });
        Cinema_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Cinema News", Toast.LENGTH_SHORT).show();
            }
        });
        Farming_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Farming News", Toast.LENGTH_SHORT).show();
            }
        });
        Industries_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Industries News", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }




    // Buttons hooks
    private void Category_btnHooks(View view) {

        Recent_btn = view.findViewById(R.id.Recent);
        Top_btn = view.findViewById(R.id.TopNews);
        Recommend_btn = view.findViewById(R.id.Recommended);
        Politics_btn = view.findViewById(R.id.Politics);
        Education_btn = view.findViewById(R.id.Education);
        Environment_btn = view.findViewById(R.id.Environment);
        Corona_btn = view.findViewById(R.id.Corona);
        Cinema_btn = view.findViewById(R.id.Cinema);
        Farming_btn = view.findViewById(R.id.Farming);
        Industries_btn = view.findViewById(R.id.Industries);




    }


}