package com.project.imagery.gallery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.imagery.R;
import com.project.imagery.journal.JournalActivity;
import com.project.imagery.singletons.FilePathHelper;
import com.project.imagery.tabhost.FrontPageTabHost;

public class GalleryEditPostActivity extends AppCompatActivity {
    Uri selectedImageUri;
    ImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_edit_post);

        Intent intent = getIntent();
        final int index = Integer.parseInt(intent.getStringExtra("index"));

        final EditText editTitle = (EditText) findViewById(R.id.GalleryEditImageTitle);
        final EditText editDescription = (EditText) findViewById(R.id.GalleryEditImageDescription);
        selectedImage = (ImageView) findViewById(R.id.editImage);

        editTitle.setText(GalleryActivity.journalPostTitle[index]);
        editDescription.setText(GalleryActivity.JournalPostDescription[index]);
        selectedImage.setImageURI(GalleryActivity.JournalPostImageUri[index]);

        Button edit = (Button) findViewById(R.id.GalleryEditButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String description = editDescription.getText().toString();
                GalleryActivity.editJournalPost(index, title, description, selectedImageUri);

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
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

                selectedImageUri = FilePathHelper.getUriFromImage(data, cursor);
                selectedImage.setImageURI(selectedImageUri);
            } else {
                new Toast(getApplicationContext()).makeText(getApplicationContext(),
                        "Not the expected file format", Toast.LENGTH_LONG).show();
            }
        }
    }
}
