package com.sdmitriy.firebasetestapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.presenter.PlacesListFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PlacesListFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.places_list)
    RecyclerView placesList;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_places_message)
    TextView emptyListMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.place_list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        PlacesListFragmentPresenter presenter = new PlacesListFragmentPresenter(this);
        presenter.setUpAdapter(placesList);
        presenter.retrievePlaces();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            presenter.retrievePlaces();
        });
        return view;
    }

    public void showEmptyListMessage() {
        emptyListMessage.setVisibility(View.VISIBLE);
    }

    public void hideEmptyListMessage() {
        emptyListMessage.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
