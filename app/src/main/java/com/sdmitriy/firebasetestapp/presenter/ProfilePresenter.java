package com.sdmitriy.firebasetestapp.presenter;

import android.content.Intent;

import com.sdmitriy.firebasetestapp.LoginActivity;
import com.sdmitriy.firebasetestapp.fragment.ProfileFragment;
import com.sdmitriy.firebasetestapp.util.Utils;

public class ProfilePresenter {

    private ProfileFragment fragment;

    public ProfilePresenter(ProfileFragment fragment) {
        this.fragment = fragment;
    }

    public void logOutUser() {
        Utils.deleteUserDataFromDharedPrefernces(fragment.getContext());
        restartFromLoginActivity();
    }

    private void restartFromLoginActivity() {
        Intent intent = new Intent(fragment.getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        fragment.startActivity(intent);
        fragment.getActivity().finish();
    }
}
