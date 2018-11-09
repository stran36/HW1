package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>
{

    ArrayList<NewsItem>newsItems;
    Context context;

    public NewsAdapter(ArrayList<NewsItem> newsContent, Context context) {
        this.newsItems = newsContent;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recyclerview, parent, false);
        NewsViewHolder vh = new NewsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, final int position)
    {
        holder.title.setText(newsItems.get(position).getTitle());
        holder.description.setText(newsItems.get(position).getDescription());
        holder.date.setText(newsItems.get(position).getPublishedAt());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String m = newsItems.get(position).getUrl();

                openWebPage(m);
            }
        });


    }
    public void openWebPage(String url) {

        Uri webpage = Uri.parse(url);

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            webpage = Uri.parse("http://" + url);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {

        return newsItems.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private TextView description;
        private TextView date;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            date = (TextView)itemView.findViewById(R.id.date);


        }
    }
}
