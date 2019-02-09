package com.project.imagery;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei on 12/9/2016.
 */
//Metode prin care convertim unitatile monetare folosind HTTPrequest pe paginile de internet care contin JSON de pe http://fixer.io
public class CurrencyConverter extends AsyncTask<String,Void,List<Currency>>{

    private List<Currency> listaMonede = new ArrayList<>();

    @Override
    protected List<Currency> doInBackground(String... params) {


        String urlString = MessageFormat.format("http://api.fixer.io/latest?base={0}&symbols={1}",params[0],params[1]);



        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);

            urlConnection.setDoOutput(true);

            urlConnection.connect();

            String jsonString = new InputStreamReader(url.openStream()).toString();//TODO: AICI E GRESIT TREBUIE STRINGBUILDER?

        } catch (IOException e) {
            e.printStackTrace();
        }





        return null;

    }
}
