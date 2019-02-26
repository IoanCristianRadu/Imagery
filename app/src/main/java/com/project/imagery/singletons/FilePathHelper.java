package com.project.imagery.singletons;

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

    public static Uri getUriFromImage(Intent data, Cursor cursor){
        Uri selectedImage = data.getData();
        String filePath = getFilePathFromUri(selectedImage, cursor);
        String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);

        if (fileExtension.equals("img") || fileExtension.equals("jpg") ||
                fileExtension.equals("jpeg") || fileExtension.equals("gif") || fileExtension.equals("png")) {
            return selectedImage;
        }
        return null;
    }

    private static String getFilePathFromUri(Uri uri,Cursor cursor) throws NullPointerException {
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String imagePath = cursor.getString(columnIndex);
        cursor.close();
        return imagePath;
    }
}
