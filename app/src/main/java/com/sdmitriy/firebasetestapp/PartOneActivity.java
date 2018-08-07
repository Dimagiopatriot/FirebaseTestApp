package com.sdmitriy.firebasetestapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
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
                setTitle(R.string.title_list);
                Utils.navigateToFragment(this, new PlacesListFragment(),
                        R.id.main_activity_container, PLACES_FRAGMENT);
                return true;
            case R.id.places_map:
                setTitle(R.string.title_map);
                Utils.navigateToFragment(this, new MapFragment(),
                        R.id.main_activity_container, MAP_FRAGMENT);
                return true;
            case R.id.profile:
                setTitle(R.string.title_profile);
                Utils.navigateToFragment(this, new ProfileFragment(),
                        R.id.main_activity_container, PROFILE_FRAGMENT);
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
        setTitle(R.string.title_list);
        Utils.navigateToFragment(this, new PlacesListFragment(),
                R.id.main_activity_container, PLACES_FRAGMENT);

        ButterKnife.bind(this);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
