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
import com.project.imagery.singletons.FilePathHelper;
import com.project.imagery.tabhost.FrontPageTabHost;

public class GalleryNewPostActivity extends AppCompatActivity {
    private ImageView selectedImage;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_new_post);

        selectedImage = (ImageView) findViewById(R.id.GalleryStampImage);
        selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);
            }
        });

        Button post = (Button) findViewById(R.id.GalleryBtnPost);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedImageUri == null){
                    new Toast(getApplicationContext()).makeText(getApplicationContext(),
                            "No image selected", Toast.LENGTH_LONG).show();
                } else{
                    EditText title  = (EditText)findViewById(R.id.GalleryImageTitle);
                    EditText description = (EditText)findViewById(R.id.GalleryDescription);
                    GalleryActivity.addJournalPost(selectedImageUri,title.getText().toString(),description.getText().toString());
                    Intent i = new Intent(getApplicationContext(), FrontPageTabHost.class);
                    startActivity(i);
                }
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

                selectedImageUri = FilePathHelper.getUriFromImage(data,cursor);
                selectedImage.setImageURI(selectedImageUri);
            } else {
                new Toast(getApplicationContext()).makeText(getApplicationContext(),
                        "Not the expected file format", Toast.LENGTH_LONG).show();
            }
        }
    }
}
