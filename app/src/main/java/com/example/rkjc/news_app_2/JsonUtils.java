package com.example.rkjc.news_app_2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils
{
    public static ArrayList<NewsItem>parseNews(JSONObject jsonObject) throws JSONException
    {
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        JSONArray jArray = jsonObject.getJSONArray("articles");
        if (jArray != null) {
            for (int i=0;i<jArray.length();i++){
                JSONObject article = jArray.getJSONObject(i);
                NewsItem newsItem = new NewsItem(
                        article.getString("author"),
                        article.getString("title"),
                        article.getString("description"),
                        article.getString("url"),
                        article.getString("urlToImage"),
                        article.getString("publishedAt")
                );
                newsItems.add(newsItem);
            }
        }
        return newsItems;
    }

}

