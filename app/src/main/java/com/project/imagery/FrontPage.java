package com.project.imagery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class FrontPage extends AppCompatActivity {
    Button post;

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
        setContentView(R.layout.activity_journal);

        CustomList adapter = new CustomList(FrontPage.this, journalText, imageId);
        listaTimbre = (ListView) findViewById(R.id.listview_timbre);
        listaTimbre.setAdapter(adapter);
        listaTimbre.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(FrontPage.this,
                        "You Clicked at " + journalText[+position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
