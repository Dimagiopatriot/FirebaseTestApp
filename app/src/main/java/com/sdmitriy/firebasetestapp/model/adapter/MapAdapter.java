package com.sdmitriy.firebasetestapp.model.adapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.entity.Place;
import com.sdmitriy.firebasetestapp.presenter.MapFragmentPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dmitriysmishnyi on 06.08.18.
 */

public class MapAdapter implements Adapter<Place> {

    private List<Place> places = new ArrayList<>();
    private Map<Place, Marker> markerMap = new HashMap<>();
    private GoogleMap map;
    private MapFragmentPresenter presenter;

    public MapAdapter(GoogleMap map, MapFragmentPresenter presenter) {
        this.map = map;
        this.presenter = presenter;
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
        presenter.showConcretePlaceInfoWindow();
        presenter.addItemsToClusterManager(places);
    }

    private void notifySetDataChanged() {
        for (Place place : places) {
            createMarker(place);
        }
    }

    public void createMarker(Place place) {
        Marker marker = map.addMarker(new MarkerOptions()
                .position(new LatLng(place.getLatitude(), place.getLongitude()))
                .title(place.getPlaceName())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker)));
        markerMap.put(place, marker);
    }

    public Marker findMarker(Place place) {
        return markerMap.containsKey(place) ? markerMap.get(place) : map.addMarker(new MarkerOptions()
                .position(new LatLng(place.getLatitude(), place.getLongitude()))
                .title(place.getPlaceName())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker)));
    }
}
