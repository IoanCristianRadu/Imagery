package com.project.imagery.journal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.project.imagery.R;
import com.project.imagery.gallery.GalleryActivity;
import com.project.imagery.singletons.IndexHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class JournalActivity extends AppCompatActivity {
    public final static int NUMBER_OF_POSTS = 100;
    static List<HashMap<String, String>> journalPosts = new ArrayList<>();
    static List<HashMap<String, String>> journalPostsReversed = new ArrayList<>();
    static String[] journalPostTitle = new String[NUMBER_OF_POSTS];
    static Uri[] JournalPostImageUri = new Uri[NUMBER_OF_POSTS];
    static String[] JournalPostDescription = new String[NUMBER_OF_POSTS];
    static Boolean[] journalPostIsGallery = new Boolean[NUMBER_OF_POSTS];
    static HashMap<Integer, Integer> galleryIndexCorrespondent = new HashMap<>();
    static Integer galleryNumber = 0;
    IndexHelper indexHelper = IndexHelper.getInstance();

    public static void addJournalPost(ImageView selectedImage, Uri selectedImageUri, String title, String description) {
        int index = journalPosts.size();
        journalPostTitle[index] = title;
        JournalPostImageUri[index] = selectedImageUri;
        JournalPostDescription[index] = description;
        HashMap<String, String> hm = new HashMap<>();
        hm.put("listview_title", journalPostTitle[index]);
        hm.put("listview_description", JournalPostDescription[index]);
        hm.put("listview_image", JournalPostImageUri[index].toString());
        journalPostIsGallery[index] = false;
        journalPosts.add(hm);
    }

    public static void createContentListReversed() {
        journalPostsReversed.clear();
        for (int index = 0; index < journalPosts.size(); index++) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("listview_title", journalPostTitle[index]);
            hm.put("listview_description", JournalPostDescription[index]);
            hm.put("listview_image", JournalPostImageUri[index].toString());
            journalPostsReversed.add(hm);
        }
        Collections.reverse(journalPostsReversed);
    }

    public static void editJournalPost(int index, String title, String description, Uri uri) {
        journalPostTitle[index] = title;
        JournalPostDescription[index] = description;
        if (uri != null) {
            JournalPostImageUri[index] = uri;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        createContentListReversed();

        String[] from = {"listview_image", "listview_title", "listview_description"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), journalPostsReversed, R.layout.journal_customlist, from, to);
        ListView journalListView = findViewById(R.id.list_view);
        journalListView.setAdapter(simpleAdapter);

        //Edit a post
        journalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (journalPostIsGallery[position]) {
                    Intent galleryActivity = new Intent(getApplicationContext(), GalleryActivity.class);
                    galleryActivity.putExtra("index", galleryIndexCorrespondent.get(getReversedIndex(position)));
                    startActivity(galleryActivity);
                } else {
                    Intent editPost = new Intent(getApplicationContext(), JournalEditPostActivity.class);
                    editPost.putExtra("index", String.valueOf(getReversedIndex(position)));
                    startActivity(editPost);
                }
            }

            private Integer getReversedIndex(int position) {
                return indexHelper.getReverseIndex(journalPosts.size()).get(position);
            }
        });
    }
}

