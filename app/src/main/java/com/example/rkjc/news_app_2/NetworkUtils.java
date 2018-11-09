package com.example.rkjc.news_app_2;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {


    public static final String BASE = "newsapi.org";
    public static final String API_KEY = "77d4192360434c08a359e97da122f252";

    public static String jsonString;
    private static JsonUtils jsonUtils;

    public static URL buildUrl() throws MalformedURLException {

        Uri.Builder buildURL = new Uri.Builder();
        buildURL.scheme("https")
                .authority(BASE)
                .appendPath("v1")
                .appendPath("articles")
                .appendQueryParameter("source", "the-next-web")
                .appendQueryParameter("sortBy", "latest&apiKey")
                .appendQueryParameter("apiKey", API_KEY);

        URL url = new URL(buildURL.toString());

        return url;

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException,JSONException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {

            InputStream in = urlConnection.getInputStream();


            Scanner scanner = new Scanner(in);

            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {

                jsonString = scanner.next();
                JSONObject jsonObject = new JSONObject(jsonString);

                jsonUtils.parseNews(jsonObject);
                return jsonString;

            } else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }

    }

    class NewsQueryTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params)
        {

            try {
                jsonString = NetworkUtils.getResponseFromHttpUrl(buildUrl());

            } catch (Exception e) {
               e.getStackTrace();
            }

            return  jsonString;
        }

        protected String onPostExecute(Boolean result)
        {

            return null;
        }
    }
}
