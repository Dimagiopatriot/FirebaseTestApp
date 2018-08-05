package com.sdmitriy.firebasetestapp.model.dao;

import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sdmitriy.firebasetestapp.model.adapter.CommonRecyclerViewAdapter;
import com.sdmitriy.firebasetestapp.model.adapter.FirebasePlaceListAdapter;
import com.sdmitriy.firebasetestapp.model.entity.Place;
import com.sdmitriy.firebasetestapp.util.Constants;

public class FirebaseDaoImpl implements FirebaseDao {

    private static class Holder {
        static final FirebaseDaoImpl INSTANCE = new FirebaseDaoImpl();
    }

    public static FirebaseDaoImpl getInstance() {
        return Holder.INSTANCE;
    }

    private DatabaseReference reference;

    private FirebaseDaoImpl() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    private CommonRecyclerViewAdapter<Place, FirebasePlaceListAdapter.Holder> listAdapter;

    @Override
    public void addPlaceToFirebase(Place place) {
        place.setUid(reference.child(Constants.TABLE_NAME).push().getKey());
        reference.child(Constants.TABLE_NAME).child(place.getUid()).setValue(place);
    }

    @Override
    public void removePlaceFromFirebase(Place place) {
        reference.child(Constants.TABLE_NAME).child(place.getUid()).removeValue();
    }

    @Override
    public <T, VH extends RecyclerView.ViewHolder> void getPlaceListFromFirebase(CommonRecyclerViewAdapter<T, VH> adapter) {
        reference.child(Constants.TABLE_NAME).addValueEventListener(new FirebaseValueEventListener((CommonRecyclerViewAdapter<Place, FirebasePlaceListAdapter.Holder>) adapter));
    }
}
