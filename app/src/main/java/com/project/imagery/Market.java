package com.project.imagery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Market extends AppCompatActivity {
    ListView listaTimbre;
    public static String[] journalText = {
            "Canadian stamp",
            "American stamp",
            "Italian stamp",
            "Washington stamp",
            "The Statue of Liberty stamp",
    };
    public static Integer[] imageId = {
            R.drawable.timbra_canada,
            R.drawable.america,
            R.drawable.stamp_italian,
            R.drawable.washington,
            R.drawable.liberty,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        CustomList adapter = new CustomList(Market.this, journalText, imageId);
        listaTimbre = (ListView) findViewById(R.id.listViewMarket);
        listaTimbre.setAdapter(adapter);
        listaTimbre.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Market.this,
                        "You Clicked at " + journalText[+position], Toast.LENGTH_SHORT).show();
            }
        });

        Button checkCurrencies = (Button) findViewById(R.id.btn_check_currencies);
        checkCurrencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), CurrencyDisplay.class);
                startActivity(it);
            }
        });
    }
}