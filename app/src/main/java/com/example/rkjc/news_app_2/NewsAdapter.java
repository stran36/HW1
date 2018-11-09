package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;


public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {
    private ArrayList<NewsItem> newsItem;
    Context context;

    public class NewsItemViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle;
        public TextView textViewDescription;
        public TextView textViewDate;
        public NewsItemViewHolder(View view) {
            super(view);
            textViewTitle =(TextView) view.findViewById(R.id.title);
            textViewDescription =(TextView) view.findViewById(R.id.description);
            textViewDate =(TextView) view.findViewById(R.id.date);
        }
    }
    public NewsAdapter(ArrayList<NewsItem> newsItem, Context context){
        this.newsItem = newsItem;
        this.context = context;
    }
    public int getItemCount(){
        return newsItem.size();
    }
    @Override
    @NonNull
    public NewsAdapter.NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_recylcerview, parent, false);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view);
        return viewHolder;
    }
    @Override
    @NonNull
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, final int position){
        holder.textViewTitle.setText(newsItem.get(position).getTitle());
        holder.textViewDescription.setText(newsItem.get(position).getDescription());
        holder.textViewDate.setText(newsItem.get(position).getPublishedAt());
        holder.textViewDescription.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String url = newsItem.get(position).getUrl();
                openWebPage(url);
            }
        });

    }
    public void openWebPage(String url){
        Uri page = Uri.parse(url);


        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            page = Uri.parse("http://" + url);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, page);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

