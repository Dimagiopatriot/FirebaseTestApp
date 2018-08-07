package com.sdmitriy.firebasetestapp.model.dao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.sdmitriy.firebasetestapp.model.adapter.Adapter;
import com.sdmitriy.firebasetestapp.model.entity.Place;

import java.util.ArrayList;
import java.util.List;

public class FirebaseValueEventListener implements ValueEventListener {

    private Adapter<Place> listAdapter;

    public FirebaseValueEventListener(Adapter<Place> listAdapter) {
        this.listAdapter = listAdapter;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        List<Place> places = new ArrayList<>();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Place place = snapshot.getValue(Place.class);
            places.add(place);
        }
        listAdapter.addItems(places);
        listAdapter.onDataChangedResponse();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        //nothing to do
    }
}
