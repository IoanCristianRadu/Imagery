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

        CurrencyRequester currencyRequester = new CurrencyRequester() {
            @Override
            protected void onPostExecute(List<Currency> currencies) {
                ListView listView = (ListView) findViewById(R.id.listViewCurrencyDisplay);
                List<String> listaCurrencies = new ArrayList<>();
                for (Currency c : currencies) {
                    listaCurrencies.add(c.getName() + " : " + c.getValue());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listaCurrencies);
                listView.setAdapter(adapter);
            }
        };
        currencyRequester.execute();
    }
}
