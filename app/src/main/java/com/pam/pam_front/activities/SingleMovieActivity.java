package com.pam.pam_front.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.pam.pam_front.R;
import com.pam.pam_front.controller.CommentAdapter;
import com.pam.pam_front.controller.NewsAdapter;
import com.pam.pam_front.model.Comment;
import com.pam.pam_front.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class SingleMovieActivity extends AppCompatActivity {

    private Movie movie;
    private TextView movieTitle;
    private TextView moviePolishTitle;
    private TextView movieCountry;
    private TextView moviePlot;
    private RecyclerView commentsListRecyclerView;

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
        commentsListRecyclerView = (RecyclerView) findViewById(R.id.commentsListRecyclerView);
        commentsListRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commentsListRecyclerView.setLayoutManager(linearLayoutManager);

        CommentAdapter commentAdapter = new CommentAdapter(createRandomCommentsList(5));

        commentsListRecyclerView.setAdapter(commentAdapter);
    }

    private void setOneMovie() {
        movie = new Movie();
        movie.setId("1");
        movie.setTitle("Wedding");;
    }

    private void fillTextViews() {
        movieTitle.setText(movie.getTitle());
    }

    private List<Comment> createRandomCommentsList(int size) {
        List<Comment> result = new ArrayList<Comment>();
        for (int i=1; i <= size; i++) {
            Comment movieNews = new Comment();
            movieNews.author = getString(R.string.author) + i;
            movieNews.description= getString(R.string.comment_description) + i;
            result.add(movieNews);
        }
        return result;
    }
}
