package com.pam.pam_front.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pam.pam_front.R;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText editTextUserLogin;
    private EditText editTextUserPassword;
    private EditText editTextUserPasswordAgain;
    private String textUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        editTextUserLogin = (EditText) findViewById(R.id.userId);
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
        textUserLogin = editTextUserLogin.getText().toString();
        String textUserPassword = editTextUserPassword.getText().toString();
        String textUserPasswordAgain = editTextUserPasswordAgain.getText().toString();

        if (textUserLogin.isEmpty()) {
            editTextUserLogin.requestFocus();
            editTextUserLogin.setError(getString(R.string.validLoginEmpty));
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
