package com.sdmitriy.firebasetestapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sdmitriy.firebasetestapp.entity.UserData;

import static com.sdmitriy.firebasetestapp.util.Constants.SharedConstants.*;

public class Utils {

    private final static String PREFERENCES_NAME = "UserLoginData";

    public static void navigateToFragment(AppCompatActivity appCompatActivity, Fragment fragment, int containerId, String tag) {
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment, tag);

        Fragment curFragment = fragmentManager.findFragmentById(containerId);
        if (curFragment != null && fragment != null
                && curFragment.getTag() != null && fragment.getTag() != null
                && curFragment.getTag().equals(fragment.getTag()))
            return;
        transaction.addToBackStack(tag);

        fragment.setTargetFragment(fragmentManager.findFragmentById(containerId), Constants.SELECT);
        transaction.commitAllowingStateLoss();
    }

    public static void saveUserDataToSharedPreferences(UserData userData, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_EMAIL, userData.getEmail());
        editor.putString(USER_ID, userData.getUserId());
        editor.apply();
    }

    public static void deleteUserDataFromDharedPrefernces(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(USER_EMAIL);
        editor.remove(USER_ID);
        editor.apply();
    }

    public static UserData getUserDataFromSharedPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        String email = preferences.getString(USER_EMAIL, "");
        String id = preferences.getString(USER_ID, "");
        return new UserData(email, id);
    }
}
