package com.project.imagery.journal;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.imagery.R;
import com.project.imagery.singletons.FilePathSingleton;
import com.project.imagery.tabhost.FrontPageTabHost;

public class JournalEditPostActivity extends AppCompatActivity {
    Uri selectedImageUri;
    ImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final int index = Integer.parseInt(intent.getStringExtra("index"));
        final EditText editTitle = findViewById(R.id.editImageTitle);
        final EditText editDescription = findViewById(R.id.editImageDescription);
        selectedImage = findViewById(R.id.editImage);
        Button edit = findViewById(R.id.editButton);
        Button createGallery = findViewById(R.id.createGallery);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_edit_post);

        editTitle.setText(JournalActivity.journalPostTitle[index]);
        editDescription.setText(JournalActivity.JournalPostDescription[index]);
        selectedImage.setImageURI(JournalActivity.JournalPostImageUri[index]);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String description = editDescription.getText().toString();
                JournalActivity.editJournalPost(index, title, description, selectedImageUri);

                Intent i = new Intent(getApplicationContext(), FrontPageTabHost.class);
                startActivity(i);
            }
        });
        selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);
            }
        });

        if(JournalActivity.journalPostIsGallery[index]){
            createGallery.setVisibility(View.INVISIBLE);
        } else{
            createGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JournalActivity.journalPostIsGallery[index] = true;
                    JournalActivity.galleryIndexCorrespondent.put(index, JournalActivity.galleryNumber++);
                    Intent i = new Intent(getApplicationContext(), FrontPageTabHost.class);
                    startActivity(i);
                }
            });
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

                selectedImageUri = FilePathSingleton.getUriFromImage(data, cursor);
                selectedImage.setImageURI(selectedImageUri);
            } else {
                new Toast(getApplicationContext()).makeText(getApplicationContext(),
                        "Not the expected file format", Toast.LENGTH_LONG).show();
            }
        }
    }
}

