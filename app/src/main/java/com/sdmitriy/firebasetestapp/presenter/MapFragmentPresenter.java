package com.sdmitriy.firebasetestapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.sdmitriy.firebasetestapp.fragment.MapFragment;
import com.sdmitriy.firebasetestapp.model.adapter.MapAdapter;
import com.sdmitriy.firebasetestapp.model.dao.FirebaseDao;
import com.sdmitriy.firebasetestapp.model.dao.FirebaseDaoImpl;
import com.sdmitriy.firebasetestapp.model.entity.MarkerItem;
import com.sdmitriy.firebasetestapp.model.entity.PlaceMarkerItem;
import com.sdmitriy.firebasetestapp.model.entity.Place;
import com.sdmitriy.firebasetestapp.model.entity.UserMarkerItem;
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
    private boolean showOnce = true;
    private Location userLocation;

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
        if (concretePlace != null) {
            mapAdapter.moveCameraToPosition(concretePlace.getLatitude(), concretePlace.getLongitude(), 12.0f);
        }
        dao = FirebaseDaoImpl.getInstance();
        dao.getPlaceListFromFirebase(mapAdapter);
    }

    public void moveCameraToUserPosition(Location currentLocation) {
        mapAdapter.moveCameraToPosition(currentLocation.getLatitude(), currentLocation.getLongitude(), 12.0f);
        userLocation = currentLocation;
    }

    public void addUserMarker() {
        if (userLocation != null) {
            mapAdapter.createMarker(new UserMarkerItem(userLocation));
        }
    }

    private void initializeLocationHelper(MapFragment mapFragment) {
        if (mapFragment.getActivity() != null) {
            locationHelper = new LocationHelper(mapFragment.getActivity(), this);
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

    public void showConcretePlaceInfoWindow(MarkerItem item, Marker marker) {
        if (concretePlace != null && concretePlace.equals(((PlaceMarkerItem) item).getPlace()) && showOnce) {
            marker.showInfoWindow();
            markerId = marker.getId();
            showOnce = false;
        }
    }

    public void savePlaceToDatabase(LatLng coordinates, String placeName) {
        Utils.checkNetworkConnection(fragment.getContext());
        placeName = Utils.formatString(placeName);
        Place place = new Place(placeName, coordinates.latitude, coordinates.longitude);
        dao.addPlaceToFirebase(place);
        MarkerItem markerItem = new PlaceMarkerItem(place);
        mapAdapter.createMarker(markerItem);
    }

    public Context getContext() {
        return fragment.getContext();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (locationHelper != null) {
            locationHelper.onActivityResult(requestCode, resultCode, data);
        }
    }
}
