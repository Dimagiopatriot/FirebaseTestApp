package com.sdmitriy.firebasetestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
    public void goToPartOne(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.part_two_button)
    public void goToPartTwo() {
        Toast.makeText(this, "Not ready yet", Toast.LENGTH_SHORT).show();
    }
}
