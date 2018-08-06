package com.sdmitriy.firebasetestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sdmitriy.firebasetestapp.presenter.LoginActivityPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    LoginActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginActivityPresenter(this);
        presenter.buildGoogleApiClient();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_via_google_plus)
    public void onSignButtonClicked() {
        presenter.signIn();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.connectGoogleClient();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.disconnectGoogleClient();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResultCallback(requestCode, data);
    }
}
