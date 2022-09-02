package com.example.news_ui.RSS_assetsCricket;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.news_ui.RSS_Assets.Connector;
import com.example.news_ui.RSS_Assets.ErrorTracker;
import com.example.news_ui.RSS_Assets.RSS_Parser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class CrickDownloader extends AsyncTask<Void,Void,Object> {

    Context CrickCont;
    RecyclerView CrickRecycler;
    String UrlAddress;

    ProgressDialog Progs;

    public CrickDownloader(Context CrickContext, RecyclerView CrickRecyclerView, String CrickAddress){

        this.CrickCont = CrickContext;
        this.CrickRecycler = CrickRecyclerView;
        this.UrlAddress = CrickAddress;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Progs=new ProgressDialog(CrickCont);
        Progs.setTitle("Fetching Article");
        Progs.setMessage("Please wait article is fetching");
        Progs.show();
    }


    @Override
    protected Object doInBackground(Void... voids) {
        return download_data();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Progs.dismiss();
        if (o.toString().startsWith("Error")){
            Toast.makeText(CrickCont, o.toString(), Toast.LENGTH_SHORT).show();
        }else {
            new RSS_ParserCrick(CrickCont, (InputStream) o, CrickRecycler).execute();
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
