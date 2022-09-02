package com.example.news_ui.Search_recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_ui.Frag_ItemListenter.SearchItemListener;
import com.example.news_ui.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context contS;
    ArrayList<Search_helper> helpersSearch;

    public SearchAdapter(Context contS,ArrayList<Search_helper> helpersSearch) {
        this.contS = contS;
        this.helpersSearch = helpersSearch;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_layout,parent,false);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        Search_helper helper = helpersSearch.get(position);

        String title = helper.getSearchTitle();
        String ImgUrl = helper.getSearchImageUrl().replace("timesofindia.indiatimes.com","timesofindia.indiatimes.com");
        String Date = helper.getSearchDate();
        String Desc = helper.getSearchDesc();




        holder.search_title.setText(title);
        if (Desc == " "){
            holder.search_desc.setText("No Description Available");
        }
            holder.search_desc.setText(Desc);


        holder.search_date.setText(Date);
        PiccasoClient.DownloadImage(contS,ImgUrl,holder.search_Image);



    }

    @Override
    public int getItemCount() {
        return helpersSearch.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{


        ImageView search_Image;
        TextView search_title,search_desc,search_date;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), SearchItemListener.class);
                    i.putExtra("title",helpersSearch.get(getPosition()).getSearchTitle());
                    i.putExtra("image",helpersSearch.get(getPosition()).getSearchImageUrl());
                    i.putExtra("description",helpersSearch.get(getPosition()).getSearchDesc());
                    i.putExtra("date",helpersSearch.get(getPosition()).getSearchDate());
                    v.getContext().startActivity(i);
                }

            });


            search_Image = itemView.findViewById(R.id.SearchImage);
            search_title = itemView.findViewById(R.id.SearchTitleText);
            search_desc = itemView.findViewById(R.id.Search_desc);
            search_date = itemView.findViewById(R.id.Date_text);

        }
    }
}
