package com.project.imagery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDisplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_display);

        CurrencyControler currencyControler = new CurrencyControler() {
            @Override
            protected void onPostExecute(List<Currency> currencies) {
                ListView listView = (ListView) findViewById(R.id.listViewCurrencyDisplay);
                List<String> displayCurrencies = new ArrayList<>();
                for (Currency currency : currencies) {
                    displayCurrencies.add(currency.getName() + " : " + currency.getValue());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, displayCurrencies);
                listView.setAdapter(adapter);
            }
        };
        currencyControler.execute();
    }
}
