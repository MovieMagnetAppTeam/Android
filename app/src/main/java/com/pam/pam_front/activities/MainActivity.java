package com.pam.pam_front.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.pam.pam_front.R;
import com.pam.pam_front.controller.PagerAdapter;
import com.pam.pam_front.fragments.HomeFragment;
import com.pam.pam_front.fragments.MovieFragment;
import com.pam.pam_front.fragments.NewsFragment;
import com.pam.pam_front.fragments.TvFragment;
import com.pam.pam_front.sharedPrefs.SharedPrefsManager;

public class MainActivity extends AppCompatActivity {

    private String userLogin;
    private SharedPrefsManager sharedPrefsManager;
    private Button showMovieButton;
    private BottomNavigationBar bottomNavigationBar;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefsManager = new SharedPrefsManager(this);

        userLogin = sharedPrefsManager.getLoggedUserLogin();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        Fragment[] fragments = {
                Fragment.instantiate(this, HomeFragment.class.getName()),
                Fragment.instantiate(this, MovieFragment.class.getName()),
                Fragment.instantiate(this, TvFragment.class.getName()),
                Fragment.instantiate(this, NewsFragment.class.getName()),
        };
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position, true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        bottomNavigationBar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                viewPager.setCurrentItem(position, true);
            }
        });
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }
    private void doMySearch(String query){
        //TODO: Add here integration with backend :)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                onSearchRequested();

                return true;

            case R.id.action_my_account:

                Intent intent = new Intent(getApplicationContext(), UsersProfileActivity.class);
                startActivity(intent);
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    private void startSingleMovieActivity() {
        Intent intent = new Intent(getApplicationContext(), SingleMovieActivity.class);
        startActivity(intent);
    }
}
