package com.project.imagery.tabhost;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.project.imagery.R;
import com.project.imagery.account.Login;
import com.project.imagery.journal.JournalActivity;
import com.project.imagery.journal.JournalNewPostActivity;

public class FrontPageTabHost extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page_tab_host);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        //Making the tabs
        TabHost.TabSpec tab1 = tabHost.newTabSpec("First tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");

        //Setting tab name and the activity it opens
        tab1.setIndicator("Journal");
        tab1.setContent(new Intent(this, JournalActivity.class));

        tab2.setIndicator("New post");
        tab2.setContent(new Intent(this, JournalNewPostActivity.class));

        tab3.setIndicator("Account");
        tab3.setContent(new Intent(this, Login.class));

        //Add tabs to the display
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
    }
}
