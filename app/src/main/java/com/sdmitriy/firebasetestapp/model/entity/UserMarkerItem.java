package com.sdmitriy.firebasetestapp.model.entity;

import android.location.Location;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sdmitriy.firebasetestapp.R;

/**
 * Created by dmitriysmishnyi on 09.08.18.
 */

public class UserMarkerItem extends MarkerItem {

    private Location userLocation;

    public UserMarkerItem(Location userLocation) {
        this.userLocation = userLocation;
        setMarker(new MarkerOptions()
                .position(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_user_marker)));
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
