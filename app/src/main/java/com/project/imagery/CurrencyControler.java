package com.project.imagery;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CurrencyControler extends AsyncTask<String, Void, List<Currency>> {
    private List<Currency> currencies = new ArrayList<>();

    @Override
    protected List<Currency> doInBackground(String... params) {
        try {
            URL url = new URL("http://api.fixer.io/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder json = new StringBuilder();
            String jsonLine = null;
            while ((jsonLine = bufferedReader.readLine()) != null) {
                json.append(jsonLine);
            }
            JSONObject jsonObject = new JSONObject(json.toString());
            JSONObject obj = jsonObject.getJSONObject("rates");
            currencies.add(new Currency("AUD", obj.getDouble("AUD")));
            currencies.add(new Currency("BGN", obj.getDouble("BGN")));
            currencies.add(new Currency("BRL", obj.getDouble("BRL")));
            currencies.add(new Currency("CAD", obj.getDouble("CAD")));
            currencies.add(new Currency("CHF", obj.getDouble("CHF")));
            currencies.add(new Currency("CNY", obj.getDouble("CNY")));
            currencies.add(new Currency("CZK", obj.getDouble("CZK")));
            currencies.add(new Currency("DKK", obj.getDouble("DKK")));
            currencies.add(new Currency("GBP", obj.getDouble("GBP")));
            currencies.add(new Currency("HKD", obj.getDouble("HKD")));
            currencies.add(new Currency("HRK", obj.getDouble("HRK")));
            currencies.add(new Currency("HUF", obj.getDouble("HUF")));
            currencies.add(new Currency("IDR", obj.getDouble("IDR")));
            currencies.add(new Currency("ILS", obj.getDouble("ILS")));
            currencies.add(new Currency("INR", obj.getDouble("INR")));
            currencies.add(new Currency("JPY", obj.getDouble("JPY")));
            currencies.add(new Currency("KRW", obj.getDouble("KRW")));
            currencies.add(new Currency("MXN", obj.getDouble("MXN")));
            currencies.add(new Currency("MYR", obj.getDouble("MYR")));
            currencies.add(new Currency("NOK", obj.getDouble("NOK")));
            currencies.add(new Currency("NZD", obj.getDouble("NZD")));
            currencies.add(new Currency("PHP", obj.getDouble("PHP")));
            currencies.add(new Currency("PLN", obj.getDouble("PLN")));
            currencies.add(new Currency("RON", obj.getDouble("RON")));
            currencies.add(new Currency("RUB", obj.getDouble("RUB")));
            currencies.add(new Currency("SEK", obj.getDouble("SEK")));
            currencies.add(new Currency("SGD", obj.getDouble("SGD")));
            currencies.add(new Currency("THB", obj.getDouble("THB")));
            currencies.add(new Currency("TRY", obj.getDouble("TRY")));
            currencies.add(new Currency("USD", obj.getDouble("USD")));
            currencies.add(new Currency("ZAR", obj.getDouble("ZAR")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return currencies;
    }
}
