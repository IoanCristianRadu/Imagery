package com.project.imagery;

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

import java.util.HashMap;

public class EditJournalPost extends AppCompatActivity {
    Uri uri;
    ImageView editImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journal_post);

        Intent intent = getIntent();
        final int index = Integer.parseInt(intent.getStringExtra("index"));

        final EditText editTitle = (EditText) findViewById(R.id.editImageTitle);
        final EditText editDescription = (EditText) findViewById(R.id.editImageDescription);
        editImage = (ImageView) findViewById(R.id.editImage);

        editTitle.setText(Journal.listviewTitle[index]);
        editDescription.setText(Journal.listviewShortDescription[index]);
        editImage.setImageURI(Journal.imageUris[index]);

        Button edit = (Button) findViewById(R.id.editButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String description = editDescription.getText().toString();
                Journal.editJournalPost(index, title, description, uri);

                Intent i = new Intent(getApplicationContext(), FrontPageTabHost.class);
                startActivity(i);
            }
        });

        editImage.setOnClickListener(new View.OnClickListener() {
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
                Uri selectedImage = data.getData();
                String filePath = getFilePathFromUri(selectedImage);
                String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1);

                if (file_extn.equals("img") || file_extn.equals("jpg") ||
                        file_extn.equals("jpeg") || file_extn.equals("gif") || file_extn.equals("png")) {
                    uri = selectedImage;
                    this.editImage.setImageURI(selectedImage);
                } else {
                    new Toast(getApplicationContext()).makeText(getApplicationContext(),
                            "Not the expected file format", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public String getFilePathFromUri(Uri uri) throws NullPointerException {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String imagePath = cursor.getString(column_index);
        cursor.close();
        return imagePath;
    }
}
