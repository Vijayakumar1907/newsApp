package com.example.news_ui.Saved_recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_ui.R;
import com.example.news_ui.Search_recycler.PiccasoClient;

import java.util.ArrayList;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.SavedViewHolder> {

    ArrayList<Saved_helperClass> helperClass;
    Context contextSaved;

    public SavedAdapter(Context contextSaved, ArrayList<Saved_helperClass> helperClasses) {
        this.helperClass = helperClasses;
        this.contextSaved = contextSaved;
    }

    @NonNull
    @Override
    public SavedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_item_layout,parent,false);

        return new SavedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedViewHolder holder, int position) {

        Saved_helperClass bindHelper = helperClass.get(position);


        String title = bindHelper.getSavedText();
        String Date = bindHelper.getSavedDate();
        String ImageUrl = bindHelper.getImagesSaved().replace("timesofindia.indiatimes.com","timesofindia.indiatimes.com");


        holder.SavedImageText.setText(title);
        PiccasoClient.DownloadImage(contextSaved,ImageUrl,holder.SavedImageView);
        holder.SavedDateText.setText(Date);

    }

    @Override
    public int getItemCount() {
        return helperClass.size();
    }

    public class SavedViewHolder extends RecyclerView.ViewHolder{

        ImageView SavedImageView;
        TextView SavedImageText,SavedDateText;

        public SavedViewHolder(@NonNull View itemView) {
            super(itemView);

            SavedImageView = itemView.findViewById(R.id.Saved_Image);
            SavedImageText = itemView.findViewById(R.id.Saved_titleTexts);
            SavedDateText = itemView.findViewById(R.id.CrickDate);
        }
    }
}
