package com.sdmitriy.firebasetestapp.fragment.secondpart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.util.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dmitriysmishnyi on 13.08.18.
 */

public class StartPartTwoFragment extends Fragment {

    Unbinder unbinder;
    AppCompatActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_part_two_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (AppCompatActivity) getActivity();
        return view;
    }


    @OnClick(R.id.screen_one)
    public void goToFirstScreen() {
        Utils.navigateToFragment(activity, new SingUpFragment(), R.id.fragment_container, "SignUp", true);
    }

    @OnClick(R.id.screen_two)
    public void goToSecondScreen() {
        Utils.navigateToFragment(activity, new NewsFeedFragment(), R.id.fragment_container, "NewsFeed", true);
    }


    @OnClick(R.id.screen_three)
    public void goToThirdScreen() {
        Utils.navigateToFragment(activity, new ProfileFragment(), R.id.fragment_container, "ProfilePartTwo", true);
    }

    @OnClick(R.id.screen_four)
    public void goToFourthScreen() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
