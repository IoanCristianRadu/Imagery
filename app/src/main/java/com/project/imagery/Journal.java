package com.project.imagery;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Journal extends AppCompatActivity {
    static List<HashMap<String, String>> contentList = new ArrayList<HashMap<String, String>>();

    static String[] listviewTitle = new String[100];

    /*
    static int[] listviewImage = new int[]{
            R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo
    };*/
    static Uri[] imageUris = new Uri[100];

    static String[] listviewShortDescription = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        //fillContentList();

        String[] from = {"listview_image", "listview_title", "listview_description"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), contentList, R.layout.journal_customlist, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);
    }

    public static void addJournalPost(ImageView selectedImage, Uri selectedImageUri){
        int index = contentList.size();
        listviewTitle[index] = "ListView Title " + index;
        imageUris[index] = selectedImageUri;
        listviewShortDescription[index] = "Android ListView Short Description";
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("listview_title", listviewTitle[index]);
        hm.put("listview_description", listviewShortDescription[index]);
        hm.put("listview_image", imageUris[index].toString());
        contentList.add(hm);
    }

    /*
    private void fillContentList(){
        for (int i = 0; i < listviewTitle.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_description", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            contentList.add(hm);
        }
    }*/
}

