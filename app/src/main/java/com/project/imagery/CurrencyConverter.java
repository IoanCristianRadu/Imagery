package com.project.imagery;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

// Get currency values as a json from http://fixer.io
public class CurrencyConverter extends AsyncTask<String, Void, List<Currency>> {
    private List<Currency> currencies = new ArrayList<>();

    @Override
    protected List<Currency> doInBackground(String... params) {
        String urlString = MessageFormat.format("http://api.fixer.io/latest?base={0}&symbols={1}", params[0], params[1]);
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            //TODO: Stringbuilder?
            String jsonString = new InputStreamReader(url.openStream()).toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
