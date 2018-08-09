package com.sdmitriy.firebasetestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sdmitriy.firebasetestapp.fragment.MapFragment;
import com.sdmitriy.firebasetestapp.fragment.PlacesListFragment;
import com.sdmitriy.firebasetestapp.fragment.ProfileFragment;
import com.sdmitriy.firebasetestapp.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.sdmitriy.firebasetestapp.util.Constants.Tags.*;

public class PartOneActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.places_list:
                Utils.navigateToFragment(this, new PlacesListFragment(),
                        R.id.main_activity_container, PLACES_FRAGMENT, true);
                return true;
            case R.id.places_map:
                Utils.navigateToFragment(this, new MapFragment(),
                        R.id.main_activity_container, MAP_FRAGMENT, true);
                return true;
            case R.id.profile:
                Utils.navigateToFragment(this, new ProfileFragment(),
                        R.id.main_activity_container, PROFILE_FRAGMENT, true);
                return true;
        }
        return false;
    };

    @BindView(R.id.navigation)
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_one);
        Utils.navigateToFragment(this, new PlacesListFragment(),
                R.id.main_activity_container, PLACES_FRAGMENT, false);

        ButterKnife.bind(this);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_activity_container);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
