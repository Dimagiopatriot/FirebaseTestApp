package com.sdmitriy.firebasetestapp.presenter;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.sdmitriy.firebasetestapp.fragment.MapFragment;
import com.sdmitriy.firebasetestapp.model.adapter.MapAdapter;
import com.sdmitriy.firebasetestapp.model.dao.FirebaseDao;
import com.sdmitriy.firebasetestapp.model.dao.FirebaseDaoImpl;
import com.sdmitriy.firebasetestapp.model.entity.Place;
import com.sdmitriy.firebasetestapp.util.Constants;
import com.sdmitriy.firebasetestapp.util.LocationHelper;

public class MapFragmentPresenter {

    private MapFragment fragment;
    private LocationHelper locationHelper;
    private MapAdapter mapAdapter;

    private Place concretePlace;

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
        FirebaseDao dao = FirebaseDaoImpl.getInstance();
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
}
