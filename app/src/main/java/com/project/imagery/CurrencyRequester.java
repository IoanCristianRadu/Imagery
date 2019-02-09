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
public class CurrencyRequester extends AsyncTask<String,Void,List<Currency>>{

    private List<Currency> listaMonede = new ArrayList<>();

    @Override
    protected List<Currency> doInBackground(String... params) {
        try {
            URL url = new URL("http://api.fixer.io/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder json = new StringBuilder();
            String linie = null;
            while ((linie = bufferedReader.readLine()) != null) {
                json.append(linie);
            }

            JSONObject jsonObject = new JSONObject(json.toString());
            JSONObject obj = jsonObject.getJSONObject("rates");
            listaMonede.add(new Currency("AUD" , obj.getDouble("AUD")));
            listaMonede.add(new Currency("BGN" , obj.getDouble("BGN")));
            listaMonede.add(new Currency("BRL" , obj.getDouble("BRL")));
            listaMonede.add(new Currency("CAD" , obj.getDouble("CAD")));
            listaMonede.add(new Currency("CHF" , obj.getDouble("CHF")));
            listaMonede.add(new Currency("CNY" , obj.getDouble("CNY")));
            listaMonede.add(new Currency("CZK" , obj.getDouble("CZK")));
            listaMonede.add(new Currency("DKK" , obj.getDouble("DKK")));
            listaMonede.add(new Currency("GBP" , obj.getDouble("GBP")));
            listaMonede.add(new Currency("HKD" , obj.getDouble("HKD")));
            listaMonede.add(new Currency("HRK" , obj.getDouble("HRK")));
            listaMonede.add(new Currency("HUF" , obj.getDouble("HUF")));
            listaMonede.add(new Currency("IDR" , obj.getDouble("IDR")));
            listaMonede.add(new Currency("ILS" , obj.getDouble("ILS")));
            listaMonede.add(new Currency("INR" , obj.getDouble("INR")));
            listaMonede.add(new Currency("JPY" , obj.getDouble("JPY")));
            listaMonede.add(new Currency("KRW" , obj.getDouble("KRW")));
            listaMonede.add(new Currency("MXN" , obj.getDouble("MXN")));
            listaMonede.add(new Currency("MYR" , obj.getDouble("MYR")));
            listaMonede.add(new Currency("NOK" , obj.getDouble("NOK")));
            listaMonede.add(new Currency("NZD" , obj.getDouble("NZD")));
            listaMonede.add(new Currency("PHP" , obj.getDouble("PHP")));
            listaMonede.add(new Currency("PLN" , obj.getDouble("PLN")));
            listaMonede.add(new Currency("RON" , obj.getDouble("RON")));
            listaMonede.add(new Currency("RUB" , obj.getDouble("RUB")));
            listaMonede.add(new Currency("SEK" , obj.getDouble("SEK")));
            listaMonede.add(new Currency("SGD" , obj.getDouble("SGD")));
            listaMonede.add(new Currency("THB" , obj.getDouble("THB")));
            listaMonede.add(new Currency("TRY" , obj.getDouble("TRY")));
            listaMonede.add(new Currency("USD" , obj.getDouble("USD")));
            listaMonede.add(new Currency("ZAR" , obj.getDouble("ZAR")));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaMonede;
    }
}
