package com.pam.pam_front.sharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {

    private static String LOGGED_USER_LOGIN = "admin@admin.com";
    private static String LOGGED_USER_PASSWORD = "admin";
    public static final String USER_LOGGED_OUT_STATE = "a";
    public static final String USER_TEMPORARY_LOGIN = "a";
    public static final String USER_TEMPORARY_PASSWORD = "b";
    public static String IS_LOGGED_IN;
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

    public String getLoggedUserPassword() {
        return sharedPreferences.getString(LOGGED_USER_PASSWORD, USER_LOGGED_OUT_STATE);
    }

    public void setLoggedUserPassword(String loggedUserPassword) {
        sharedPreferences.edit()
                .putString(LOGGED_USER_PASSWORD, loggedUserPassword)
                .commit();
    }

    public boolean isIsLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        sharedPreferences.edit()
                .putBoolean(IS_LOGGED_IN, isLoggedIn)
                .commit();
    }
}
