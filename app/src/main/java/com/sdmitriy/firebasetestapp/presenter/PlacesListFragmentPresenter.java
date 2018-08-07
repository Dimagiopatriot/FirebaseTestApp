package com.sdmitriy.firebasetestapp.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.fragment.MapFragment;
import com.sdmitriy.firebasetestapp.fragment.PlacesListFragment;
import com.sdmitriy.firebasetestapp.model.adapter.FirebasePlaceListAdapter;
import com.sdmitriy.firebasetestapp.model.dao.FirebaseDao;
import com.sdmitriy.firebasetestapp.model.dao.FirebaseDaoImpl;
import com.sdmitriy.firebasetestapp.model.entity.Place;
import com.sdmitriy.firebasetestapp.util.Constants;
import com.sdmitriy.firebasetestapp.util.Utils;

public class PlacesListFragmentPresenter {

    private FirebaseDao dao;
    private PlacesListFragment fragment;

    private FirebasePlaceListAdapter adapter;

    public PlacesListFragmentPresenter(PlacesListFragment fragment) {
        dao = FirebaseDaoImpl.getInstance();
        this.fragment = fragment;
    }

    public void setUpAdapter(RecyclerView recyclerView) {
        adapter = new FirebasePlaceListAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(fragment.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void retrievePlaces() {
        dao.getPlaceListFromFirebase(adapter);
    }

    public void checkListEmptiness() {
        if (adapter.getItemCount() == 0) {
            fragment.showEmptyListMessage();
        } else {
            fragment.hideEmptyListMessage();
        }
    }

    public void removePlace(Place place) {
        dao.removePlaceFromFirebase(place);
        adapter.removeItem(place);
    }

    public void openMapFragment(Place place) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.BUNDLE_PLACE, place);
        if (fragment.getActivity() != null) {
            fragment.getActivity().setTitle(R.string.title_map);
            Utils.navigateToFragment((AppCompatActivity) fragment.getActivity(), MapFragment.getInstance(args),
                    R.id.main_activity_container, Constants.Tags.MAP_FRAGMENT);
        }
    }
}
