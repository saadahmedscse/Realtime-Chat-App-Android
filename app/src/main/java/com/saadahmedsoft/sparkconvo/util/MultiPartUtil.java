/*
 * Copyright 2018-2023 Saad Ahmed
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.saadahmedsoft.sparkconvo.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MultiPartUtil {

    /**
     * MultiPart Utility class.
     * Created by Saad Ahmed on 31, Jan 2022.
     * Github: https://github.com/saadahmedscse/Multi-Part-Utility
     * An Utility class to get MultiPartBody.Part of any Document
     */

    @SuppressLint("StaticFieldLeak")
    private static MultiPartUtil instance = null;
    private final Context context;

    private MultiPartUtil(Context context) {
        this.context = context;
    }

    /**
     * getInstance function returns the singleton instance of MultiPartUtil class
     * @param context is required getting content resolver
     * @return singleton instance of MultiPartUtil class
     */

    public static MultiPartUtil getInstance(Context context) {
        if (instance == null) {
            instance = new MultiPartUtil(context);
        }
        return instance;
    }

    /**
     * getPartFromUri function returns the created MultiPartBody.Part
     * @param uri is required for creating Part
     * @param formDataKey is required to make form data
     * @return created MultiPartBody.Part
     * @throws IOException for invalid URI
     */

    public MultipartBody.Part getPartFromUri(@NonNull Uri uri, String formDataKey) throws IOException {
        Cursor cursor = contentResolver().query(uri, null, null, null, null);
        String fileName = "";

        try {
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (columnIndex >= 0) {
                    fileName = cursor.getString(columnIndex);
                } else throw new IOException("Cursor columnIndex out of bound for index " + columnIndex);
            }
        } finally {
            if (cursor != null) cursor.close();
        }

        InputStream inputStream = contentResolver().openInputStream(uri);
        assert inputStream != null;
        byte[] fileBytes = getBytes(inputStream);

        RequestBody requestFile = RequestBody.create(fileBytes);
        return MultipartBody.Part.createFormData(formDataKey, fileName, requestFile);
    }

    /**
     * contentResolver return contentResolver
     * @return contentResolver
     */

    private ContentResolver contentResolver() {
        return context.getContentResolver();
    }

    /**
     * getBytes function generates byte from InputStream and returns it
     * @param inputStream is required for generating bytes
     * @return bytes of InputStream
     * @throws IOException for invalid URI
     */

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read;
        byte[] data = new byte[16384];

        while ((read = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, read);
        }

        return buffer.toByteArray();
    }
}
