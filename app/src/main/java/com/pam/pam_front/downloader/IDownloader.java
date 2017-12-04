package com.pam.pam_front.downloader;

import com.pam.pam_front.model.Message;
import com.pam.pam_front.model.Movie;
import com.pam.pam_front.model.User;
import com.pam.pam_front.model.UserCredentials;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IDownloader {
    @GET("search_movie")
    Call<List<Movie>> getMovie(@Query("query") String name);
    @GET("search_tv_show")
    Call<List<Movie>> getTvShow(@Query("query") String name);
    @GET("news")
    Call<List<Movie>> getNews();
    @GET("news/filter")
    Call<List<Movie>> getFilteredNews();
    @GET("get_movie_id")
    Call<Movie> getMovieById(@Query("id") Long id);
    @POST("register")
    Call<Object> registerUser(@Body User user);
    @POST("login")
    Call<Message> loginUser(@Body UserCredentials userCredentials);
}
