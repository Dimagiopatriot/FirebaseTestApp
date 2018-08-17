package com.sdmitriy.firebasetestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sdmitriy.firebasetestapp.util.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.part_one_button)
    public void goToPartOne() {
        Intent intent;
        if (Utils.isUserLoggedOn(this)) {
            intent = new Intent(this, PartOneActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
    }

    @OnClick(R.id.part_two_button)
    public void goToPartTwo() {
        Intent intent = new Intent(this, PartTwoActivity.class);
        startActivity(intent);
    }
}
