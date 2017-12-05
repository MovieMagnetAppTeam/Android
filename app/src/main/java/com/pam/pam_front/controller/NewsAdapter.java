package com.pam.pam_front.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pam.pam_front.ImageLoadTask;
import com.pam.pam_front.R;
import com.pam.pam_front.model.Movie;
import com.pam.pam_front.model.MovieNews;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by ledsoon on 20.11.17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Movie> newsList;

    public NewsAdapter(List<Movie> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.movie_news_card, viewGroup, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder newsViewHolder, int position) {
        Movie movieNews = newsList.get(position);
        newsViewHolder.movieTitleTextView.setText(movieNews.getTitle());
        newsViewHolder.newsDescriptionTextView.setText(movieNews.getDescription());
        new ImageLoadTask(movieNews.getPoster(), newsViewHolder.movieCover).execute();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitleTextView;
        TextView newsDescriptionTextView;
        ImageView movieCover;

        NewsViewHolder(View v) {
            super(v);
            movieTitleTextView =  (TextView) v.findViewById(R.id.movieTitle);
            newsDescriptionTextView = (TextView)  v.findViewById(R.id.newsDescription);
            movieCover = (ImageView)  v.findViewById(R.id.movieCover);
        }
    }
}
