package com.sdmitriy.firebasetestapp.model.adapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.sdmitriy.firebasetestapp.model.entity.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitriysmishnyi on 06.08.18.
 */

public class MapAdapter implements Adapter<Place> {

    private List<Place> places = new ArrayList<>();
    private GoogleMap map;

    public MapAdapter(GoogleMap map) {
        this.map = map;
    }

    public void moveCameraToPosition(double latitude, double longitude) {
        LatLng position = new LatLng(latitude, longitude);
        map.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    @Override
    public void addItems(List<Place> items) {
        places.addAll(items);
        notifySetDataChanged();
    }

    @Override
    public void removeItem(Place item) {
        places.remove(item);
        notifySetDataChanged();
    }

    private void notifySetDataChanged() {
        for (Place place : places) {

        }
    }

    public Marker createMarker (double latitude, double longitude, String title) {
        //todo add realization
        return null;
    }
}
