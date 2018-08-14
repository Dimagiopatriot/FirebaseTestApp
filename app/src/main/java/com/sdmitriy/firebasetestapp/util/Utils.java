package com.sdmitriy.firebasetestapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.entity.UserData;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.CategoryItem;

import java.util.ArrayList;
import java.util.List;

import static com.sdmitriy.firebasetestapp.util.Constants.SharedConstants.USER_EMAIL;
import static com.sdmitriy.firebasetestapp.util.Constants.SharedConstants.USER_ID;

public class Utils {

    private final static String PREFERENCES_NAME = "UserLoginData";

    public static void navigateToFragment(AppCompatActivity appCompatActivity, Fragment fragment,
                                          int containerId, String tag, boolean addToBackStack) {
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment, tag);

        if (addToBackStack) {
            Fragment curFragment = fragmentManager.findFragmentById(containerId);
            if (curFragment != null && fragment != null
                    && curFragment.getTag() != null && fragment.getTag() != null
                    && curFragment.getTag().equals(fragment.getTag()))
                return;
            transaction.addToBackStack(tag);
        }

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

    private static UserData getUserDataFromSharedPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        String email = preferences.getString(USER_EMAIL, "");
        String id = preferences.getString(USER_ID, "");
        return new UserData(email, id);
    }

    public static boolean isUserLoggedOn(Context context) {
        UserData userData = getUserDataFromSharedPreferences(context);
        return !userData.getEmail().equals("") && !userData.getUserId().equals("");
    }

    public static String formatString(String formattedString) {
        if (formattedString.equals("")) {
            return "Empty place name";
        } else {
            return formattedString;
        }
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return (netInfo != null && netInfo.isConnected());
    }

    public static void checkNetworkConnection(Context context) {
        if (context != null && !Utils.isNetworkAvailable(context)) {
            Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public static List<CategoryItem> getCategoryItemListForRecyclerView() {
        List<CategoryItem> categoryItems = new ArrayList<>();
        categoryItems.add(new CategoryItem(R.drawable.image_item_one,
                "Morbi per tincidunt tellus sit of amet eros laoreet.", 26, 32, false));
        categoryItems.add(new CategoryItem(R.drawable.image_item_two,
                "Fusce ornare cursus masspretium tortor integer placera.", 15, 21, true));
        categoryItems.add(new CategoryItem(R.drawable.image_item_three,
                "Maecenas eu risus blanscelerisque massa non amcorpe.", 36, 15, false));
        categoryItems.add(new CategoryItem(R.drawable.image_item_four,
                "Maecenas eu risus blanscelerisque massa non amcorpe.", 11, 9, false));
        return categoryItems;
    }
}
