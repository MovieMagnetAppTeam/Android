package com.pam.pam_front.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pam.pam_front.R;
import com.pam.pam_front.controller.NewsAdapter;
import com.pam.pam_front.model.MovieNews;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

private RecyclerView newsListRecyclerView;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        newsListRecyclerView = (RecyclerView) view.findViewById(R.id.newsListRecyclerView);
        newsListRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsListRecyclerView.setLayoutManager(linearLayoutManager);

        NewsAdapter newsAdapter = new NewsAdapter(createRandomNewsList(30));

        newsListRecyclerView.setAdapter(newsAdapter);
    }

    private List<MovieNews> createRandomNewsList(int size) {
        List<MovieNews> result = new ArrayList<MovieNews>();
        for (int i=1; i <= size; i++) {
            MovieNews movieNews = new MovieNews();
            movieNews.movieTitle = getString(R.string.title) + i;
            movieNews.movieNewsDescription = getString(R.string.description) + i;
            result.add(movieNews);
        }
        return result;
    }

}
