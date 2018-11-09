package com.example.rkjc.news_app_2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import org.json.JSONObject;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    NetworkUtils networkUtils;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private ArrayList<NewsItem> newsItems = new ArrayList<>();
    private JSONObject jsonObject;
    private JsonUtils jsonUtils;


    @Override

    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        start();
    }
    private void start(){
        networkUtils = new NetworkUtils();
        jsonUtils = new JsonUtils();


            try {
                String jsonString = networkUtils.new NewsQueryTask().execute().get();
                jsonObject = new JSONObject(jsonString);
                newsItems = jsonUtils.parseNews(jsonObject);
                adapter = new NewsAdapter(newsItems, this);
            } catch (Exception e) {
                e.getStackTrace();
            }



        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        try {
            if (jsonObject == null) {
                return;
            }String jsonString = networkUtils.new NewsQueryTask().execute().get();
            jsonObject = new JSONObject(jsonString);

            newsItems = jsonUtils.parseNews(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }


        adapter = new NewsAdapter(newsItems, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.get_news) {
            start();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
