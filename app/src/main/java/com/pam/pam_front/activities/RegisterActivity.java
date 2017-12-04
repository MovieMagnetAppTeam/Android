package com.pam.pam_front.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pam.pam_front.R;
import com.pam.pam_front.downloader.MovieDownloader;
import com.pam.pam_front.model.User;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextUserEmail;
    private EditText editTextUserPassword;
    private EditText editTextUserPasswordAgain;
    private String textUserLogin;
    private MovieDownloader movieDownloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        movieDownloader = new MovieDownloader(this);
        initViews();
    }

    private void initViews() {
        editTextName = (EditText) findViewById(R.id.userName);
        editTextLastName = (EditText) findViewById(R.id.userLastName);
        editTextUserEmail = (EditText) findViewById(R.id.userEmail);
        editTextUserPassword = (EditText) findViewById(R.id.userPassword);
        editTextUserPasswordAgain = (EditText) findViewById(R.id.userPasswordAgain);
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        if (!validate()) {
            onValidateFailed();
            return;
        }

        User user = new User(editTextName.getText().toString(), editTextLastName.getText().toString(), editTextUserEmail.getText().toString(), editTextUserPassword.getText().toString());
        movieDownloader.registerUser(user);
        startLoginActivity();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void onValidateFailed() {
        Toast.makeText(RegisterActivity.this, R.string.validFail, Toast.LENGTH_SHORT).show();
        registerButton.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;
        textUserLogin = editTextUserEmail.getText().toString();
        String textUserPassword = editTextUserPassword.getText().toString();
        String textUserPasswordAgain = editTextUserPasswordAgain.getText().toString();

        if (editTextName.getText().toString().isEmpty()) {
            editTextName.requestFocus();
            editTextName.setError(getString(R.string.type_your_name));
            valid = false;
        }

        if (editTextLastName.getText().toString().isEmpty()) {
            editTextLastName.requestFocus();
            editTextLastName.setError(getString(R.string.type_your_last_name));
            valid = false;
        }

        if (textUserLogin.isEmpty()) {
            editTextUserEmail.requestFocus();
            editTextUserEmail.setError(getString(R.string.validLoginEmpty));
            valid = false;
        }

        if (textUserPassword.isEmpty()) {
            if(valid) {
                editTextUserPassword.requestFocus();
            }
            editTextUserPassword.setError(getString(R.string.validPasswordEmpty));
            valid = false;
        }

        if (textUserPasswordAgain.isEmpty()) {
            if(valid) {
                editTextUserPasswordAgain.requestFocus();
            }
            editTextUserPasswordAgain.setError(getString(R.string.validPasswordEmptyAgain));
            valid = false;
        } else if (!textUserPasswordAgain.equals(textUserPassword)) {
            if(valid) {
                editTextUserPasswordAgain.requestFocus();
            }
            editTextUserPasswordAgain.setError(getString(R.string.validPasswordAgainNotCorrect));
            valid = false;
        }

        return valid;
    }
}
