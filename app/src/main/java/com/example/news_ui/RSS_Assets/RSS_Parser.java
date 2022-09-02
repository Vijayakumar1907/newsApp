package com.example.news_ui.RSS_Assets;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.news_ui.Search_recycler.SearchAdapter;
import com.example.news_ui.Search_recycler.Search_helper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@SuppressLint("StaticFieldLeak")
public class RSS_Parser extends AsyncTask<Void,Void,Boolean> {


    Context context;
    InputStream is;
    RecyclerView recyclerView;

    ArrayList<Search_helper> S_helper = new ArrayList<>();
   // ArrayList<SearchDetailedHelper> SDhelper = new ArrayList<>();

    public RSS_Parser(Context context, InputStream is, RecyclerView recyclerView) {
        this.context = context;
        this.is = is;
        this.recyclerView = recyclerView;
    }

    ProgressDialog progs;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
         progs = new ProgressDialog(context);
         progs.setTitle("Parsing Article");
         progs.setMessage("Please wait detail are parsing");
         progs.show();

    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.ParseRSS();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        progs.dismiss();
        if (isParsed){
            recyclerView.setAdapter(new SearchAdapter(context,S_helper));
        }
        else {
            Toast.makeText(context, "Unable to parse", Toast.LENGTH_SHORT).show();
        }

        // Binding



    }


    private Boolean ParseRSS(){

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(is,null);
            int event = parser.getEventType();

            String tagValue = null;
            Boolean isSiteMeta = true;
            S_helper.clear();

            Search_helper searchHelper = new Search_helper();


            do {
                String tagName = parser.getName();
                switch (event){

                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")){
                            searchHelper = new Search_helper();
                            isSiteMeta = false;

                        }
                        break;

                    case XmlPullParser.TEXT:
                        tagValue = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (!isSiteMeta){
                            if (tagName.equalsIgnoreCase("title")){
                                String title = tagValue;
                                searchHelper.setSearchTitle(title);


                            }else if (tagName.equalsIgnoreCase("description")){
                                // Extract image form the description
                                String desc = tagValue;
                                if (desc==" "){
                                    searchHelper.setSearchDesc("Description not available");
                                }
                                searchHelper.setSearchDesc(desc.substring(desc.indexOf("/>")+2));




                                String ImageUrl = desc.substring(desc.indexOf("src=")+5,desc.indexOf("cms")+3);
                                searchHelper.setSearchImageUrl(ImageUrl);
                            }else if (tagName.equalsIgnoreCase("pubDate")){
                                //String date = tagValue;
                                searchHelper.setSearchDate(tagValue);
                            }
                        }

                        if (tagName.equalsIgnoreCase("item")){
                            S_helper.add(searchHelper);
                            isSiteMeta = true;
                        }

                        break;
                }

                event = parser.next();
            }while (event!=XmlPullParser.END_DOCUMENT);

            return true;
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        return false;

    }
}
