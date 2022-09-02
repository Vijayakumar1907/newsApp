package com.example.news_ui.RSS_assetsCricket;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.news_ui.Saved_recycler.SavedAdapter;
import com.example.news_ui.Saved_recycler.Saved_helperClass;
import com.example.news_ui.Search_recycler.SearchAdapter;
import com.example.news_ui.Search_recycler.Search_helper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RSS_ParserCrick extends AsyncTask<Void,Void,Boolean> {

    @SuppressLint("StaticFieldLeak")
    Context RssCrickCont;
    InputStream RssInput;
    RecyclerView RssCrickRecycler;

    // Declaring the progerss dialog
    ProgressDialog CrickPdg;

    ArrayList<Saved_helperClass> helperClasses = new ArrayList<>();


    public RSS_ParserCrick(Context rssCrickCont, InputStream RssInput, RecyclerView rssCrickRecycler) {
        this.RssCrickCont = rssCrickCont;
        this.RssCrickRecycler = rssCrickRecycler;
        this.RssInput = RssInput;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        CrickPdg = new ProgressDialog(RssCrickCont);
        CrickPdg.setTitle("Parsing Article");
        CrickPdg.setMessage("Please wait the article are parsing");
        CrickPdg.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.CrikPassRss();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);
        isParsed=true;

        CrickPdg.dismiss();
        if (isParsed){
            RssCrickRecycler.setAdapter(new SavedAdapter(RssCrickCont, helperClasses));
        }
        else {
            Toast.makeText(RssCrickCont, "Unable to parse", Toast.LENGTH_SHORT).show();
        }
    }


    private Boolean CrikPassRss(){

        try {
            XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser Parser = pullParserFactory.newPullParser();

            Parser.setInput(RssInput,null);
            int eventType = Parser.getEventType();

            String tagValue = null;
            Boolean isSiteMeta = true;
            helperClasses.clear();

            Saved_helperClass saved_helperClass = new Saved_helperClass();


            do {
                String tagName = Parser.getName();
                switch (eventType){

                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")){
                            saved_helperClass = new Saved_helperClass();
                            isSiteMeta = false;

                        }
                        break;

                    case XmlPullParser.TEXT:
                        tagValue = Parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (!isSiteMeta){
                            if (tagName.equalsIgnoreCase("title")){
                                String title = tagValue;
                                saved_helperClass.setSavedText(title);


                            }else if (tagName.equalsIgnoreCase("description")){
                                // Extract image form the description
                                String desc = tagValue;
                                if (desc==" "){
                                    saved_helperClass.setSaveddesc("Description not available");
                                }
                                saved_helperClass.setSaveddesc(desc.substring(desc.indexOf("/>")+2));




                                String ImageUrl = desc.substring(desc.indexOf("src=")+5,desc.indexOf("cms")+3);
                                saved_helperClass.setImagesSaved(ImageUrl);
                            }else if (tagName.equalsIgnoreCase("pubDate")){
                                //String date = tagValue;
                                saved_helperClass.setSavedDate(tagValue);
                            }
                        }

                        if (tagName.equalsIgnoreCase("item")){
                            helperClasses.add(saved_helperClass);
                            isSiteMeta = true;
                        }

                        break;
                }

                eventType = Parser.next();
            }while (eventType!=XmlPullParser.END_DOCUMENT);

            return false;
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
