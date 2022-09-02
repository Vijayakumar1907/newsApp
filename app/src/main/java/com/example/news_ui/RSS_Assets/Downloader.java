package com.example.news_ui.RSS_Assets;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class Downloader extends AsyncTask<Void,Void,Object> {

    Context cont;
    RecyclerView recyclerView;
    String UrlAddress;

    public Downloader(Context cont, RecyclerView recyclerView, String urlAddress) {
        this.cont = cont;
        this.recyclerView = recyclerView;
        this.UrlAddress = urlAddress;
    }

    ProgressDialog pdg;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pdg = new ProgressDialog(cont);
        pdg.setTitle("Fetching Article");
        pdg.setMessage("Fetching... Please wait for a while");
        pdg.show();
    }

    @Override
    protected Object doInBackground(Void... voids) {
        return download_data();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        pdg.dismiss();
        if (o.toString().startsWith("Error")){
            Toast.makeText(cont, o.toString(), Toast.LENGTH_SHORT).show();
        }else {
            new RSS_Parser(cont, (InputStream) o,recyclerView).execute();
        }
    }

    private Object download_data(){

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
