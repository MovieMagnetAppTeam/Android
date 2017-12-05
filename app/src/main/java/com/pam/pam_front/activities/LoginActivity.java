package com.pam.pam_front.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pam.pam_front.R;
import com.pam.pam_front.downloader.MovieDownloader;
import com.pam.pam_front.model.IResponse;
import com.pam.pam_front.model.UserCredentials;
import com.pam.pam_front.sharedPrefs.SharedPrefsManager;

import java.util.List;


public class LoginActivity extends AppCompatActivity implements IResponse{

    private Button loginButton;
    private Button registerButton;
    private EditText editTextUserLogin;
    private EditText editTextUserPassword;
    private SharedPrefsManager sharedPrefsManager;
    private String textUserLogin;
    private MovieDownloader movieDownloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefsManager = new SharedPrefsManager(this);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        editTextUserLogin = (EditText) findViewById(R.id.userEmail);
        editTextUserPassword = (EditText) findViewById(R.id.userPassword);
        editTextUserPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login();
                    handled = true;
                }
                return handled;
            }
        });
        int color;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = ContextCompat.getColor(this, R.color.colorPrimary);
        } else {
            color = getResources().getColor(R.color.colorPrimary);
        }
        editTextUserLogin.getBackground().mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        editTextUserPassword.getBackground().mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisterActivity();
            }
        });
        editTextUserLogin.setText(sharedPrefsManager.getLoggedUserLogin());
        editTextUserPassword.setText(sharedPrefsManager.getLoggedUserPassword());
    }

    private void login() {
        sharedPrefsManager.setLoggedUserLogin(editTextUserLogin.getText().toString());
        sharedPrefsManager.setLoggedUserPassword(editTextUserPassword.getText().toString());
        movieDownloader = new MovieDownloader(this, this);
        movieDownloader.loginUser(new UserCredentials(editTextUserLogin.getText().toString(), editTextUserPassword.getText().toString()));
        if (!validate()) {
            return;
        }
        loginButton.setEnabled(false);
    }

    private void startRegisterActivity() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onValidateSuccess() {
        loginButton.setEnabled(true);
        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void onValidateFailed() {
        Toast.makeText(LoginActivity.this, R.string.validFail, Toast.LENGTH_SHORT).show();
        loginButton.setEnabled(true);
        sharedPrefsManager.setLoggedUserLogin("");
        sharedPrefsManager.setLoggedUserPassword("");
    }

    private boolean validate() {
        boolean valid = true;
        textUserLogin = editTextUserLogin.getText().toString();
        String textUserPassword = editTextUserPassword.getText().toString();

        if (textUserLogin.isEmpty()) {
            editTextUserLogin.requestFocus();
            editTextUserLogin.setError(getString(R.string.validLogin));
            valid = false;
        }
        if (textUserPassword.isEmpty()) {
            editTextUserPassword.requestFocus();
            editTextUserPassword.setError(getString(R.string.validPassword));
            valid = false;
        }
        return valid;
    }

    @Override
    public void succeed() {
        onValidateSuccess();
    }

    @Override
    public void failure() {
        onValidateFailed();
    }

    @Override
    public void setList(List list) {

    }
}
