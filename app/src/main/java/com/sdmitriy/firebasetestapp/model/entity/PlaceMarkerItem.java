package com.sdmitriy.firebasetestapp.model.entity;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.sdmitriy.firebasetestapp.R;

/**
 * Created by dmitriysmishnyi on 07.08.18.
 */

public class PlaceMarkerItem extends MarkerItem {

    private Place place;

    public PlaceMarkerItem(Place place) {
        this.place = place;
        setMarker(new MarkerOptions()
                .position(new LatLng(place.getLatitude(), place.getLongitude()))
                .title(place.getPlaceName())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker)));
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(place.getLatitude(), place.getLongitude());
    }

    @Override
    public String getTitle() {
        return place.getPlaceName();
    }

    @Override
    public String getSnippet() {
        return null;
    }

    public Place getPlace() {
        return place;
    }
}
