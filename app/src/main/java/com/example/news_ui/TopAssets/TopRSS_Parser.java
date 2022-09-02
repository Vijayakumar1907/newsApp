package com.example.news_ui.TopAssets;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TopRSS_Parser extends AsyncTask<Void,Void,Boolean> {

    Context ParserContext;
    InputStream is;
    SliderView sliderView;

    ArrayList<TopHelper> helperTop = new ArrayList<>();

    ProgressDialog progressDialog;
    public TopRSS_Parser(Context parserContext, InputStream is, SliderView sliderView) {
        this.ParserContext = parserContext;
        this.is = is;
        this.sliderView = sliderView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(ParserContext);
        progressDialog.setTitle("Parsing");
        progressDialog.setMessage("Parsing the detail please wait");
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return TopPass();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        isParsed = true;
        progressDialog.dismiss();

        if (isParsed){
            sliderView.setSliderAdapter(new TopAdapter(ParserContext,helperTop));
            sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
            sliderView.setAutoCycle(true);
            sliderView.startAutoCycle();
        }else {
            Toast.makeText(ParserContext, "Unable to parse", Toast.LENGTH_SHORT).show();
        }

    }

    private Boolean TopPass(){

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factory.newPullParser();

            pullParser.setInput(is,null);
            int event = pullParser.getEventType();

            String tagValue = null;
            boolean isSiteMeta = true;
            helperTop.clear();

            TopHelper helper = new TopHelper();

            do {
                String tagName = pullParser.getName();

                switch (event){

                    case XmlPullParser.START_TAG:
                        if (isSiteMeta){
                            if (tagName.equalsIgnoreCase("title")){
                                String title = tagValue;
                                helper.setTitle(title);
                            } else if (tagName.equalsIgnoreCase("description")){
                                String desc = tagValue;

                                String ImageUrl = desc.substring(desc.indexOf("src=")+5,desc.indexOf("cms")+3);
                                helper.setImageUrl(ImageUrl);
                            } else if (tagName.equalsIgnoreCase("pubDate")){
                                helper.setDate(tagValue);
                            }
                        }

                        if (tagName.equalsIgnoreCase("item")){
                            helperTop.add(helper);
                            isSiteMeta = true;
                        }
                        break;
                }

                event  = pullParser.next();
            }while (event != XmlPullParser.END_DOCUMENT);
            return false;


        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
