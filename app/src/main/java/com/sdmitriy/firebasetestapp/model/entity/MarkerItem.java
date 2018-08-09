package com.sdmitriy.firebasetestapp.model.entity;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by dmitriysmishnyi on 09.08.18.
 */

public abstract class MarkerItem implements ClusterItem {

    private MarkerOptions marker;

    @Override
    public LatLng getPosition() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }

    public void setMarker(MarkerOptions marker) {
        this.marker = marker;
    }

    public MarkerOptions getMarker() {
        return marker;
    }
}
