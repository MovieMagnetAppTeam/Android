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
//        try {
//            newsViewHolder.movieCover.setImageBitmap(BitmapFactory.decodeStream(new URL(movieNews.getPoster()).openConnection().getInputStream()));
//        } catch (IOException e) {
//            Log.d("URLException", e.toString());
//        }
//        URL newurl = null;
//        try {
//            newurl = new URL(movieNews.getPoster());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Bitmap bitmap = null;
//        try {
//            bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        newsViewHolder.movieCover.setImageBitmap(getBitmapFromURL(movieNews.getPoster()));
        new ImageLoadTask(movieNews.getPoster(), newsViewHolder.movieCover).execute();
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
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
