package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils
{
    public static ArrayList<NewsItem>parseNews(JSONObject jsonObject) throws JSONException
    {
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        JSONArray jArray = jsonObject.getJSONArray("articles");
        if (jArray != null) {
            for (int i=0;i<jArray.length();i++){
                NewsItem newsItem = new NewsItem();
                JSONObject article = jArray.getJSONObject(i);
                String title = article.getString("title");
                String desc = article.getString("description");
                String url = article.getString("url");
                String urlToImage = article.getString("urlToImage");
                String date = article.getString("publishedAt");


                newsItem.setTitle(title);
                newsItem.setDescription(desc);
                newsItem.setPublishedAt(date);
                newsItem.setUrl(url);
                newsItems.add(newsItem);

            }
        }
        return newsItems;
    }

}

