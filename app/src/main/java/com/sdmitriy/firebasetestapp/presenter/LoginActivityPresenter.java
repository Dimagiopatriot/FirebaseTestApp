package com.sdmitriy.firebasetestapp.presenter;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sdmitriy.firebasetestapp.LoginActivity;
import com.sdmitriy.firebasetestapp.PartOneActivity;
import com.sdmitriy.firebasetestapp.model.entity.UserData;
import com.sdmitriy.firebasetestapp.util.Constants;
import com.sdmitriy.firebasetestapp.util.Utils;

public class LoginActivityPresenter implements GoogleApiClient.OnConnectionFailedListener {

    private LoginActivity activity;
    private GoogleApiClient apiClient;

    public LoginActivityPresenter(LoginActivity activity) {
        this.activity = activity;
    }

    public synchronized void buildGoogleApiClient() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        apiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
    }

    public void connectGoogleClient() {
        apiClient.connect();
    }

    public void disconnectGoogleClient() {
        apiClient.stopAutoManage(activity);
        apiClient.disconnect();
    }

    public void signIn() {
        if (apiClient.hasConnectedApi(Auth.GOOGLE_SIGN_IN_API)) {
            apiClient.clearDefaultAccountAndReconnect();
        }
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        activity.startActivityForResult(signInIntent, Constants.SIGN_IN_RESULT);
    }

    public void onActivityResultCallback(int requestCode, Intent data) {
        if (requestCode == Constants.SIGN_IN_RESULT) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            if (account != null) {
                UserData userData = new UserData(account.getEmail(), account.getId());
                Utils.saveUserDataToSharedPreferences(userData, activity);
            }
            startMainActivity();
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(activity, PartOneActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
        });
    }
}
