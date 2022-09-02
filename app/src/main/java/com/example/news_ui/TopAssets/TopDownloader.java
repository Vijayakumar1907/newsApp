package com.example.news_ui.TopAssets;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.news_ui.RSS_Assets.Connector;
import com.example.news_ui.RSS_Assets.ErrorTracker;
import com.example.news_ui.RSS_assetsCricket.RSS_ParserCrick;
import com.smarteist.autoimageslider.SliderView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class TopDownloader extends AsyncTask<Void,Void,Object> {

    Context TopContext;
    SliderView sliderView;
    String UrlAddress;

    ProgressDialog T_prog;

    public TopDownloader(Context topContext, SliderView sliderView, String urlAddress) {
        this.TopContext = topContext;
        this.sliderView = sliderView;
        this.UrlAddress = urlAddress;
    }

    @Override
    protected void onPreExecute() {

        T_prog = new ProgressDialog(TopContext);
        T_prog.setTitle("Fetching...");
        T_prog.setMessage("Please wait the article is fetching");
        T_prog.show();

        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... voids) {
        return TopDownloader();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        T_prog.dismiss();
        if (o.toString().startsWith("Error")){
            Toast.makeText(TopContext, o.toString(), Toast.LENGTH_SHORT).show();
        }else {
            new TopRSS_Parser(TopContext, (InputStream) o, sliderView).execute();
        }
    }

    private Object TopDownloader(){

        // Accessing the Connection from the connector class Arguments Passed (UrlAddress)
        Object connection = Connector.connect(UrlAddress);

        // if the any error made during connection it must starts with the "Error" String
        if (connection.toString().startsWith("Error")){
            return connection.toString(); // Returning that error message
        }
        // Or else
        try {
            HttpURLConnection conn = (HttpURLConnection) connection;
            // assigning the value of the connection (con.getResponseCode) to ResponseCode int variable
            int ResponseCode = conn.getResponseCode();

            if (ResponseCode == conn.HTTP_OK){
                InputStream inputStream = new BufferedInputStream(conn.getInputStream());
                return inputStream;
            }

            return ErrorTracker.RESPONSE_ERROR+conn.getResponseMessage();

        } catch (IOException e) {
            e.printStackTrace();
            return ErrorTracker.IO_ERROR;
        }


    }
}
