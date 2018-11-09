package com.example.rkjc.news_app_2;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Scanner;

class NetworkUtils {
    static final String BASE_URL = "https://newsapi.org/v1/articles";
    static final String QUERY_PARAMETER_1 = "source";
    static final String QUERY_PARAMETER_2 = "sortBy";
    static final String QUERY_PARAMETER_3 = "apiKey";
    public static String jsonString;
    private static JsonUtils jsonUtils;
    public static URL buildURL()throws MalformedURLException{
        Uri constructedURL = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_1, "the-next-web")
                .appendQueryParameter(QUERY_PARAMETER_2, "latest")
                .appendQueryParameter(QUERY_PARAMETER_3, "77d4192360434c08a359e97da122f252")
                .build();
            URL url = new URL(constructedURL.toString());
            return url;

    }
    public static String getResponseFromHttpUrl(URL url)throws IOException,JSONException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try{
                InputStream in = urlConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");
                boolean hasInput = scanner.hasNext();
                if(hasInput){
                    jsonString = scanner.next();
                    JSONObject jobj = new JSONObject(jsonString);

                    jsonUtils.parseNews(jobj);
                    return jsonString;
                }else{
                    return null;
                }
            }finally {
                urlConnection.disconnect();
            }
    }
    class NewsQueryTask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params){
            try {
                jsonString = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
            }catch(Exception e){
                e.getStackTrace();
            }
            return jsonString;
        }
        protected String onPostExecute(Boolean result){
            return null;
        }
    }
}



