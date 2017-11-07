package com.pam.pam_front.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.pam.pam_front.R;
import com.pam.pam_front.sharedPrefs.SharedPrefsManager;

public class MainActivity extends AppCompatActivity {

    private String userLogin;
    private SharedPrefsManager sharedPrefsManager;
    private Button showMovieButton;
    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefsManager = new SharedPrefsManager(this);

        userLogin = sharedPrefsManager.getLoggedUserLogin();

        showMovieButton = (Button) findViewById(R.id.showRandomMovie);

        showMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSingleMovieActivity();
            }
        });

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottomNavigationBar);
        bottomNavigationBar.addTab(new BottomBarItem(R.drawable.ic_home, R.string.home));
        bottomNavigationBar.addTab(new BottomBarItem(R.drawable.ic_movie, R.string.movie));
        bottomNavigationBar.addTab(new BottomBarItem(R.drawable.ic_tv, R.string.tv));
        bottomNavigationBar.addTab(new BottomBarItem(R.drawable.ic_news, R.string.news));
        bottomNavigationBar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                //Obsluga menu - position to pozycja wybranego przycisku
            }
        });
    }

    private void startSingleMovieActivity() {
        Intent intent = new Intent(getApplicationContext(), SingleMovieActivity.class);
        startActivity(intent);
    }
}
