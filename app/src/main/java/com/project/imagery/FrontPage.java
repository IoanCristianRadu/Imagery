package com.project.imagery;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FrontPage extends AppCompatActivity {
    Button post;

    ListView listaTimbre;
    public static String[] web = {
            "Timbra Canada",
            "Timbra America",
            "Timbra Italia",
            "Timbra Washington",
            "Timbra Statuia Libertatii",
    } ;
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
        setContentView(R.layout.activity_front_page);

        CustomList adapter = new CustomList(FrontPage.this, web, imageId);
        listaTimbre=(ListView)findViewById(R.id.listview_timbre);
        listaTimbre.setAdapter(adapter);
        listaTimbre.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(FrontPage.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
            }
        });



        post = (Button)findViewById(R.id.btn_post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),AddPostActivity.class);
                startActivity(it);
            }
        });

    }
}
