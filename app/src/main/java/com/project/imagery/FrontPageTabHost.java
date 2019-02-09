package com.project.imagery;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class FrontPageTabHost extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page_tab_host);


        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        //Making the tabs
        TabHost.TabSpec tab1 = tabHost.newTabSpec("First tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");

        //Setting tab name and the activity it opens
        tab1.setIndicator("Front page");
        tab1.setContent(new Intent(this,FrontPage.class));

        tab2.setIndicator("Market");
        tab2.setContent(new Intent(this,Market.class));

        tab3.setIndicator("Account");
        tab3.setContent(new Intent(this,Login.class));

        //Add tabs to the display
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

    }
}