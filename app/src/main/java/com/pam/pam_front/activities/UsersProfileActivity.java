package com.pam.pam_front.activities;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pam.pam_front.R;
import com.pam.pam_front.downloader.MovieDownloader;
import com.pam.pam_front.model.IResponse;
import com.pam.pam_front.sharedPrefs.SharedPrefsManager;
import com.pam.pam_front.model.Movie;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class UsersProfileActivity extends AppCompatActivity implements IResponse {

    private List<String> tags;
    private EditText newTagEditText, usersNameEditText;
    private Button addTagButton;
    TagContainerLayout tagContainerLayout;
    SharedPrefsManager sharedPrefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);
        newTagEditText = (EditText) findViewById(R.id.newTagEditText);
        usersNameEditText = (EditText) findViewById(R.id.usersNameEditText);
        addTagButton = (Button) findViewById(R.id.addTagButton);
        Toolbar usersProfileActivityToolbar = (Toolbar) findViewById(R.id.users_profile_activity_toolbar);
        setSupportActionBar(usersProfileActivityToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getUserTags();
        tags = new ArrayList<>();
        sharedPrefsManager = new SharedPrefsManager(this);
        usersNameEditText.setText(sharedPrefsManager.getLoggedUserLogin());
        tagContainerLayout = (TagContainerLayout) findViewById(R.id.tagContainerLayout);
        tagContainerLayout.setTags(tags);
        tagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {

            }

            @Override
            public void onTagLongClick(int position, String text) {
                tagContainerLayout.removeTag(position);
            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });
        addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newTagEditText.getText().length() > 0){
                    tagContainerLayout.addTag(newTagEditText.getText().toString());
                    newTagEditText.setText("");
                    newTagEditText.clearFocus();
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }else {
                    Toast.makeText(UsersProfileActivity.this, R.string.you_cant_add_empty_tag, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getUserTags() {
        MovieDownloader movieDownloader = new MovieDownloader(this, this);
        movieDownloader.getUserTags();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void succeed() {

    }

    @Override
    public void succeed(List<Movie> movies) {

    }

    @Override
    public void failure() {

    }

    @Override
    public void setList(List tagNames) {
        tagContainerLayout.setTags(tagNames);

    }
}
