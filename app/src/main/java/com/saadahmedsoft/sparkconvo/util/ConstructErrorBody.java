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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;

public class ConstructErrorBody {
    @Nullable
    public static String getMessage(@NonNull ResponseBody errorBody) {
        InputStream byteStream = errorBody.byteStream();
        StringBuilder sb = new StringBuilder();
        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(byteStream));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) { e.printStackTrace(); }

        try {
            return new JSONObject(sb.toString()).getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}