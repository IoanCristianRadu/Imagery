package com.project.imagery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Journal extends AppCompatActivity {
    static List<HashMap<String, String>> contentList = new ArrayList<HashMap<String, String>>();
    static List<HashMap<String, String>> contentListReversed = new ArrayList<HashMap<String, String>>();
    HashMap<Integer,Integer> reverseIndex = new HashMap<>();
    static String[] listviewTitle = new String[100];
    static Uri[] imageUris = new Uri[100];
    static String[] listviewShortDescription = new String[100];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        createContentListReversed();
        populateReverseIndex();

        String[] from = {"listview_image", "listview_title", "listview_description"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), contentListReversed, R.layout.journal_customlist, from, to);
        ListView journalListView = (ListView) findViewById(R.id.list_view);

        //Edit a post
        journalListView.setAdapter(simpleAdapter);
        journalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent editPost = new Intent(getApplicationContext(), EditJournalPost.class);
                int reversedIndex = reverseIndex.get(position);
                String number = "" + reversedIndex;
                editPost.putExtra("index", number);
                startActivity(editPost);
            }
        });
    }

    public static void addJournalPost(ImageView selectedImage, Uri selectedImageUri,String title, String description){
        int index = contentList.size();
        listviewTitle[index] = title;
        imageUris[index] = selectedImageUri;
        listviewShortDescription[index] = description;
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("listview_title", listviewTitle[index]);
        hm.put("listview_description", listviewShortDescription[index]);
        hm.put("listview_image", imageUris[index].toString());
        contentList.add(hm);
    }

    public static void editJournalPost(int index, String title, String description, Uri uri){
        listviewTitle[index] = title;
        listviewShortDescription[index] = description;
        if(uri != null){
            imageUris[index] = uri;
        }
        //HashMap<String, String> element = new HashMap<String, String>();
        //element.put("listview_title", Journal.listviewTitle[index]);
        //element.put("listview_description", Journal.listviewShortDescription[index]);
        //element.put("listview_image", Journal.imageUris[index].toString());

        //Journal.contentList.set(index, element);
    }

    public static void createContentListReversed(){
        contentListReversed.clear();

        for(int index=0;index<contentList.size();index++){
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[index]);
            hm.put("listview_description", listviewShortDescription[index]);
            hm.put("listview_image", imageUris[index].toString());
            contentListReversed.add(hm);
        }
        Collections.reverse(contentListReversed);
    }

    private void populateReverseIndex(){
        for(int i=0;i<contentList.size();i++){
            reverseIndex.put(i,contentList.size()-i-1);
        }
    }
}

