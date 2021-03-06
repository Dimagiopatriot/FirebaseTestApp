package com.sdmitriy.firebasetestapp.model.adapter;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;
import com.sdmitriy.firebasetestapp.model.entity.MarkerItem;
import com.sdmitriy.firebasetestapp.model.entity.PlaceMarkerItem;
import com.sdmitriy.firebasetestapp.model.entity.Place;
import com.sdmitriy.firebasetestapp.presenter.MapFragmentPresenter;
import com.sdmitriy.firebasetestapp.util.CustomClusterRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitriysmishnyi on 06.08.18.
 */

public class MapAdapter implements Adapter<Place> {

    private List<Place> places = new ArrayList<>();
    private GoogleMap map;
    private MapFragmentPresenter presenter;
    private ClusterManager<MarkerItem> clusterManager;

    public MapAdapter(GoogleMap map, MapFragmentPresenter presenter) {
        this.map = map;
        this.presenter = presenter;
        setUpClusterManager(presenter.getContext());
    }

    public void moveCameraToPosition(double latitude, double longitude, float zoomLevel) {
        LatLng position = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, zoomLevel));
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

    @Override
    public void onDataChangedResponse() {
    }

    private void notifySetDataChanged() {
        List<MarkerItem> markers = new ArrayList<>();
        clusterManager.clearItems();
        for (Place place : places) {
            MarkerItem placeMarkerItem = new PlaceMarkerItem(place);
            markers.add(placeMarkerItem);
        }
        presenter.addUserMarker();
        clusterManager.addItems(markers);
        clusterManager.cluster();
    }

    public void createMarker(MarkerItem markerItem) {
        clusterManager.addItem(markerItem);
        clusterManager.cluster();
    }

    private void setUpClusterManager(Context context) {
        clusterManager = new ClusterManager<>(context, map);
        clusterManager.setOnClusterClickListener((cluster) -> {
            LatLng clusterPosition = cluster.getPosition();
            moveCameraToPosition(clusterPosition.latitude, clusterPosition.longitude, 0.0f);
            return false;
        });
        clusterManager.setOnClusterItemClickListener((markerItem -> {
            CustomClusterRenderer renderer = (CustomClusterRenderer) clusterManager.getRenderer();
            Marker marker = renderer.getMarker(markerItem);
            return presenter.showHideInfoWindow(marker);
        }));
        clusterManager.setRenderer(new CustomClusterRenderer(context, map, clusterManager));
        ((CustomClusterRenderer) clusterManager.getRenderer()).setPresenter(presenter);

        map.setOnCameraIdleListener(clusterManager);
        map.setOnMarkerClickListener(clusterManager);
    }
}
