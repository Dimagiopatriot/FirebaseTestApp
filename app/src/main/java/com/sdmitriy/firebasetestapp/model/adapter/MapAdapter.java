package com.sdmitriy.firebasetestapp.model.adapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sdmitriy.firebasetestapp.R;
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
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 12.0f));
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
            createMarker(place.getLatitude(), place.getLongitude(), place.getPlaceName());
        }
    }

    public void createMarker(double latitude, double longitude, String title) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker)));
    }
}
