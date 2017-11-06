package com.pam.pam_front.sharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {

    private static final String LOGGED_USER_LOGIN = "loggedUserLogin";
    public static final String USER_LOGGED_OUT_STATE = "a";
    public static final String USER_TEMPORARY_LOGIN = "a";
    public static final String USER_TEMPORARY_PASSWORD = "b";
    private SharedPreferences sharedPreferences;

    public SharedPrefsManager(Context context) {
        sharedPreferences = context.getSharedPreferences("preferences.xml", Context.MODE_PRIVATE);
    }

    public String getLoggedUserLogin() {
        return sharedPreferences.getString(LOGGED_USER_LOGIN, USER_LOGGED_OUT_STATE);
    }

    public void setLoggedUserLogin(String loggedUserId) {
        sharedPreferences.edit()
                .putString(LOGGED_USER_LOGIN, loggedUserId)
                .commit();
    }

}
