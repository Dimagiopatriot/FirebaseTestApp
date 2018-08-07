package com.sdmitriy.firebasetestapp.presenter;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.sdmitriy.firebasetestapp.fragment.MapFragment;
import com.sdmitriy.firebasetestapp.model.adapter.MapAdapter;
import com.sdmitriy.firebasetestapp.model.dao.FirebaseDao;
import com.sdmitriy.firebasetestapp.model.dao.FirebaseDaoImpl;
import com.sdmitriy.firebasetestapp.model.entity.Place;
import com.sdmitriy.firebasetestapp.util.Constants;
import com.sdmitriy.firebasetestapp.util.LocationHelper;
import com.sdmitriy.firebasetestapp.util.Utils;

public class MapFragmentPresenter {

    private MapFragment fragment;
    private LocationHelper locationHelper;
    private MapAdapter mapAdapter;
    private FirebaseDao dao;

    private Place concretePlace;
    private String markerId;

    public MapFragmentPresenter(MapFragment mapFragment) {
        this.fragment = mapFragment;
        onMapFragmentOpenStrategy(fragment.getArguments());
    }

    private void onMapFragmentOpenStrategy(Bundle args) {
        if (args == null || args.getParcelable(Constants.BUNDLE_PLACE) == null) {
            initializeLocationHelper(fragment);
        } else {
            concretePlace = args.getParcelable(Constants.BUNDLE_PLACE);
        }
    }

    public void onGoogleMapReady() {
        if (locationHelper != null && locationHelper.getLastKnownLocation() != null) {
            Location currentLocation = locationHelper.getLastKnownLocation();
            mapAdapter.moveCameraToPosition(currentLocation.getLatitude(), currentLocation.getLongitude());
        } else if (concretePlace != null) {
            mapAdapter.moveCameraToPosition(concretePlace.getLatitude(), concretePlace.getLongitude());
        }
        dao = FirebaseDaoImpl.getInstance();
        dao.getPlaceListFromFirebase(mapAdapter);
    }

    private void initializeLocationHelper(MapFragment mapFragment) {
        if (mapFragment.getActivity() != null) {
            locationHelper = new LocationHelper(mapFragment.getActivity());
            locationHelper.connectGoogleApiClient();
        }
    }

    public void onRequestPermissionsResult(int resultCode, int[] grantResults) {
        if (locationHelper != null) {
            locationHelper.onRequestPermissionResult(resultCode, grantResults);
        }
    }

    public void disconnectGoogleApiClient() {
        if (locationHelper != null) {
            locationHelper.disconnectGoogleApiClient();
        }
    }

    public void setMapAdapter(MapAdapter mapAdapter) {
        this.mapAdapter = mapAdapter;
    }

    public boolean showHideInfoWindow(Marker marker) {
        if (marker.getId().equals(markerId)) {
            markerId = "";
            marker.hideInfoWindow();
            return true;
        } else {
            markerId = marker.getId();
            marker.showInfoWindow();
            return false;
        }
    }

    public void resetMarkerId() {
        markerId = "";
    }

    public void showConcretePlaceInfoWindow() {
        if (concretePlace != null) {
            Marker concretePlaceMarker = mapAdapter.findMarker(concretePlace);
            concretePlaceMarker.showInfoWindow();
            markerId = concretePlaceMarker.getId();
        }
    }

    public void savePlaceToDatabase(LatLng coordinates, String placeName) {
        placeName = Utils.formatString(placeName);
        Place place = new Place(placeName, coordinates.latitude, coordinates.longitude);
        dao.addPlaceToFirebase(place);
        mapAdapter.createMarker(place);
    }
}
