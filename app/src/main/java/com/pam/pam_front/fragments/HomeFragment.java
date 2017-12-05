package com.pam.pam_front.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pam.pam_front.ImageLoadTask;
import com.pam.pam_front.R;
import com.pam.pam_front.activities.MainActivity;
import com.pam.pam_front.downloader.MovieDownloader;
import com.pam.pam_front.model.IResponse;
import com.pam.pam_front.model.Movie;
import com.pam.pam_front.model.MovieMock;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends Fragment implements IResponse {
    TextView movieTitle0;
    TextView movieTitle1;
    TextView movieTitle2;
    TextView movieTitle3;
    TextView movieTitle4;
    TextView movieTitle5;
    TextView movieTitle6;
    TextView movieTitle7;
    TextView movieTitle8;
    TextView movieTitle9;
    TextView movieTitle10;
    TextView movieTitle11;
    ImageView movieCover0;
    ImageView movieCover1;
    ImageView movieCover2;
    ImageView movieCover3;
    ImageView movieCover4;
    ImageView movieCover5;
    ImageView movieCover6;
    ImageView movieCover7;
    ImageView movieCover8;
    ImageView movieCover9;
    ImageView movieCover10;
    ImageView movieCover11;
    private MovieDownloader movieDownloader;
    private MovieMock movieMock;
    private List<Movie> downloadedMoviesData = new ArrayList<>();
    private static final int NUMBER_OF_VIEWS = 12;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        movieDownloader = new MovieDownloader(getContext(), this);
        downloadData();
    }

    private void downloadData() {
        movieMock = new MovieMock();
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
        movieTitle6 = (TextView) view.findViewById(R.id.movieTitle6);
        movieTitle7 = (TextView) view.findViewById(R.id.movieTitle7);
        movieTitle8 = (TextView) view.findViewById(R.id.movieTitle8);
        movieTitle9 = (TextView) view.findViewById(R.id.movieTitle9);
        movieTitle10 = (TextView) view.findViewById(R.id.movieTitle10);
        movieTitle11 = (TextView) view.findViewById(R.id.movieTitle11);
        movieCover0 = (ImageView) view.findViewById(R.id.movieCover0);
        movieCover1 = (ImageView) view.findViewById(R.id.movieCover1);
        movieCover2 = (ImageView) view.findViewById(R.id.movieCover2);
        movieCover3 = (ImageView) view.findViewById(R.id.movieCover3);
        movieCover4 = (ImageView) view.findViewById(R.id.movieCover4);
        movieCover5 = (ImageView) view.findViewById(R.id.movieCover5);
        movieCover6 = (ImageView) view.findViewById(R.id.movieCover6);
        movieCover7 = (ImageView) view.findViewById(R.id.movieCover7);
        movieCover8 = (ImageView) view.findViewById(R.id.movieCover8);
        movieCover9 = (ImageView) view.findViewById(R.id.movieCover9);
        movieCover10 = (ImageView) view.findViewById(R.id.movieCover10);
        movieCover11 = (ImageView) view.findViewById(R.id.movieCover11);

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
        movieTitle6.setText(downloadedMoviesData.get(6).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(6).getPoster(), movieCover6).execute();
        movieTitle7.setText(downloadedMoviesData.get(7).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(7).getPoster(), movieCover7).execute();
        movieTitle8.setText(downloadedMoviesData.get(8).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(8).getPoster(), movieCover8).execute();
        movieTitle9.setText(downloadedMoviesData.get(9).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(9).getPoster(), movieCover9).execute();
        movieTitle10.setText(downloadedMoviesData.get(10).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(10).getPoster(), movieCover10).execute();
        movieTitle11.setText(downloadedMoviesData.get(11).getTitle());
        new ImageLoadTask(downloadedMoviesData.get(11).getPoster(), movieCover11).execute();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
