package com.sdmitriy.firebasetestapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.presenter.ProfilePresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment {

    private Unbinder unbinder;
    private ProfilePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (getActivity() != null) {
            this.getActivity().setTitle(R.string.title_profile);
        }

        presenter = new ProfilePresenter(this);
        return view;
    }

    @OnClick(R.id.logout_button)
    public void onLogoutClick() {
        presenter.logOutUser();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
