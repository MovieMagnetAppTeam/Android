package com.pam.pam_front.downloader;

import com.pam.pam_front.model.Movie;
import com.pam.pam_front.model.User;

import java.io.IOException;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MovieDownloader {

    private final Retrofit retrofit;
//    TODO: Update to what BASE_URL backend or server has
    private final String BASE_URL = "http://192.168.1.103:8080/"; //IP ADDRESS MUST BE CHANGED
    private IDownloader iDownloader;
//    TODO: Możnaby coś z tego z sharedPrefów brać i ustawiać je przy logowaniu użytkownika
    private final String login = "admin@admin.com";
    private final String password = "admin";
    private Exception exception;

    public MovieDownloader() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic(login, password));

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build();

        iDownloader = retrofit.create(IDownloader.class);
    }

    private void searchMovie(String movieTitle) {

        Call<List<Movie>> call = iDownloader.getMovie("Fight Club");

//        TODO: Asynchronicznie, więc szybciej

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                exception = new Exception(t);
            }
        });

//        TODO: Albo od razu żądamy wyniku i czekamy na niego. Ale to może niesamowicie się mulić.

        try {
            call.execute().body();
        } catch (IOException e) {
            exception = e;
        }
        
    }

    public void registerUser(User user) {
        Call<Object> call = iDownloader.registerUser(user);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
            }
        });
    }

}
