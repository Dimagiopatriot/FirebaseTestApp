package com.sdmitriy.firebasetestapp.model.dao;

import com.sdmitriy.firebasetestapp.model.adapter.Adapter;
import com.sdmitriy.firebasetestapp.model.entity.Place;

public interface FirebaseDao {

    void addPlaceToFirebase(Place place);

    void removePlaceFromFirebase(Place place);

    <T> void getPlaceListFromFirebase(Adapter<T> adapter);
}
