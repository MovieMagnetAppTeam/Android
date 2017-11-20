package com.pam.pam_front.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pam.pam_front.R;
import com.pam.pam_front.model.MovieNews;

import java.util.List;

/**
 * Created by ledsoon on 20.11.17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<MovieNews> newsList;

    public NewsAdapter(List<MovieNews> newsList) {
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
        MovieNews movieNews = newsList.get(position);
        newsViewHolder.movieTitleTextView.setText(movieNews.movieTitle);
        newsViewHolder.newsDescriptionTextView.setText(movieNews.movieNewsDescription);

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
