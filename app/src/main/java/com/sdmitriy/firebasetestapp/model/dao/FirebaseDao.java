package com.sdmitriy.firebasetestapp.model.dao;

import android.support.v7.widget.RecyclerView;

import com.sdmitriy.firebasetestapp.model.adapter.CommonRecyclerViewAdapter;
import com.sdmitriy.firebasetestapp.model.entity.Place;

public interface FirebaseDao {

    void addPlaceToFirebase(Place place);

    void removePlaceFromFirebase(Place place);

    <T, VH extends RecyclerView.ViewHolder> void getPlaceListFromFirebase(CommonRecyclerViewAdapter<T, VH> adapter);
}
