package com.example.rkjc.news_app_2;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


import static com.example.rkjc.news_app_2.NetworkUtils.jsonString;



public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<NewsItem> newsItems = new ArrayList<>();
    private JSONObject jsonObject;
    private JsonUtils jsonUtils;
    NetworkUtils networkUtils;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        run();

    }
    private void run(){
        try {
            networkUtils = new NetworkUtils();
            jsonObject = new JSONObject(jsonString);
            newsItems = jsonUtils.parseNews(jsonObject);
            adapter = new NewsAdapter(newsItems,this);
            recyclerView.setAdapter(adapter);
        }catch(Exception e){
            e.getStackTrace();
        }
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        try {
            if (jsonObject == null) {
                return;
            }
            jsonObject = new JSONObject(jsonString);
            newsItems = jsonUtils.parseNews(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new NewsAdapter(newsItems,this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.get_news:
               run();
               return true;
            default:return super.onOptionsItemSelected(item);
        }
    }
}



