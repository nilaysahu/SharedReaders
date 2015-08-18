package com.example.nisahu.myapplication.backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by nisahu on 16/08/2015.
 */
public class HttpRequestHandler {

    public String Perform(String query) {
        try {
            URL url = new URL("https://www.goodreads.com/search.xml?key=JvO2RkMBST74HI7z0famA&q="+ URLEncoder.encode(query,"utf-8"));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {

            return e.toString();
        }
    }
}
