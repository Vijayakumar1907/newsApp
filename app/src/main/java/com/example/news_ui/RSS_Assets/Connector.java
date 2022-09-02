package com.example.news_ui.RSS_Assets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connector {

    public static Object connect(String UrlAddress){

        try {
            URL url = new URL(UrlAddress);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Setting up the properties

            con.setRequestMethod("GET");
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.setDoInput(true);

            return con;


        } catch (MalformedURLException e) {
            e.printStackTrace();

            return ErrorTracker.WRONG_URL_FORMAT;
        } catch (IOException e) {
            e.printStackTrace();

            return ErrorTracker.CONNECTION_ERROR;
        }


    }

}
