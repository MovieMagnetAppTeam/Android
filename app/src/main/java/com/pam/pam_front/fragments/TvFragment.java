package com.pam.pam_front.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pam.pam_front.ImageLoadTask;
import com.pam.pam_front.R;
import com.pam.pam_front.downloader.MovieDownloader;
import com.pam.pam_front.model.IResponse;
import com.pam.pam_front.model.Movie;
import com.pam.pam_front.model.MovieMock;

import java.util.ArrayList;
import java.util.List;


public class TvFragment extends Fragment implements IResponse{

    TextView movieTitle0;
    TextView movieTitle1;
    TextView movieTitle2;
    TextView movieTitle3;
    TextView movieTitle4;
    TextView movieTitle5;
    ImageView movieCover0;
    ImageView movieCover1;
    ImageView movieCover2;
    ImageView movieCover3;
    ImageView movieCover4;
    ImageView movieCover5;
    private MovieDownloader movieDownloader;
    private MovieMock movieMock;
    private List<Movie> downloadedMoviesData = new ArrayList<>();
    private static final int NUMBER_OF_VIEWS = 6;

    public TvFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        movieDownloader = new MovieDownloader(getContext(), this);
        downloadData();
    }

    private void downloadData() {
        movieMock = new MovieMock("tvShow");
        for(String movieTitle : movieMock.movieTitles) {
            movieDownloader.searchMovie(movieTitle);
        }
    }

    private void initViews(View view) {
        movieTitle0 = (TextView) view.findViewById(R.id.movieTitle0);
        movieTitle1 = (TextView) view.findViewById(R.id.movieTitle1);
        movieTitle2 = (TextView) view.findViewById(R.id.movieTitle2);
        movieTitle3 = (TextView) view.findViewById(R.id.movieTitle3);
        movieTitle4 = (TextView) view.findViewById(R.id.movieTitle4);
        movieTitle5 = (TextView) view.findViewById(R.id.movieTitle5);
        movieCover0 = (ImageView) view.findViewById(R.id.movieCover0);
        movieCover1 = (ImageView) view.findViewById(R.id.movieCover1);
        movieCover2 = (ImageView) view.findViewById(R.id.movieCover2);
        movieCover3 = (ImageView) view.findViewById(R.id.movieCover3);
        movieCover4 = (ImageView) view.findViewById(R.id.movieCover4);
        movieCover5 = (ImageView) view.findViewById(R.id.movieCover5);
    }

    private void setViews() {
        movieTitle0.setText(downloadedMoviesData.get(0).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(0).getPoster(), movieCover0).execute();
        movieTitle1.setText(downloadedMoviesData.get(1).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(1).getPoster(), movieCover1).execute();
        movieTitle2.setText(downloadedMoviesData.get(2).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(2).getPoster(), movieCover2).execute();
        movieTitle3.setText(downloadedMoviesData.get(3).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(3).getPoster(), movieCover3).execute();
        movieTitle4.setText(downloadedMoviesData.get(4).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(4).getPoster(), movieCover4).execute();
        movieTitle5.setText(downloadedMoviesData.get(5).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(5).getPoster(), movieCover5).execute();
    }

    @Override
    public void succeed() {

    }

    @Override
    public void succeed(List<Movie> movies) {
        downloadedMoviesData.add(movies.get(0));
        if(downloadedMoviesData.size() == NUMBER_OF_VIEWS) {
            setViews();
        }
    }

    @Override
    public void failure() {

    }

    @Override
    public void setList(List list) {

    }
}
