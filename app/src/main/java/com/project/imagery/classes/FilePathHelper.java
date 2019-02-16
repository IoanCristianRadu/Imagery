package com.project.imagery.classes;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class FilePathHelper {
    private static final FilePathHelper ourInstance = new FilePathHelper();

    public static FilePathHelper getInstance() {
        return ourInstance;
    }

    private FilePathHelper() {
    }

    public static Uri stuff(Intent data,Cursor cursor){
        Uri selectedImage = data.getData();
        String filePath = getFilePathFromUri(selectedImage, cursor);
        String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1);

        if (file_extn.equals("img") || file_extn.equals("jpg") ||
                file_extn.equals("jpeg") || file_extn.equals("gif") || file_extn.equals("png")) {
            return selectedImage;
        }
        return null;
    }

    private static String getFilePathFromUri(Uri uri,Cursor cursor) throws NullPointerException {
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String imagePath = cursor.getString(column_index);
        cursor.close();
        return imagePath;
    }
}
