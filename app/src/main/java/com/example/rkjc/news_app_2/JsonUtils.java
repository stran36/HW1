package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {
    public static ArrayList<NewsItem> parseNews(JSONObject jsonObject) {
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("articles");
            ArrayList<NewsItem> newsItems = new ArrayList();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject article = jsonArray.getJSONObject(i);
                newsItems.add(new NewsItem(article.getString("author"), article.getString("title"), article.getString("description"), article.getString("url"), article.getString("urlToImage"), article.getString("publishedAt")));
            }
            return newsItems;
        } catch (Exception e) {
            return null;
        }
    }

}


