package com.pam.pam_front.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pam.pam_front.R;
import com.pam.pam_front.model.Movie;

public class SingleMovieActivity extends AppCompatActivity {

    private Movie movie;
    private TextView movieTitle;
    private TextView moviePolishTitle;
    private TextView movieCountry;
    private TextView moviePlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setOneMovie();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillTextViews();
    }

    private void initViews() {
        movieTitle = (TextView) findViewById(R.id.movieTitle);
        moviePolishTitle = (TextView) findViewById(R.id.polishTitleInput);
        movieCountry = (TextView) findViewById(R.id.countryInput);
        moviePlot = (TextView) findViewById(R.id.plotInput);
    }

    private void setOneMovie() {
        movie = new Movie();
        movie.setId(1);
        movie.setTitle("Wedding");
        movie.setPolishTitle("Wesele");
        movie.setCountry("Polska");
        movie.setPlot("W jednej z polskich wsi odbywa się wesele.");
    }

    private void fillTextViews() {
        movieTitle.setText(movie.getTitle());
        moviePolishTitle.setText(movie.getPolishTitle());
        movieCountry.setText(movie.getCountry());
        moviePlot.setText(movie.getPlot());
    }
}
