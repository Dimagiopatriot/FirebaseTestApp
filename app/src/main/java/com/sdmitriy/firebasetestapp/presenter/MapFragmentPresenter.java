package com.sdmitriy.firebasetestapp.presenter;

import android.os.Bundle;

import com.sdmitriy.firebasetestapp.fragment.MapFragment;
import com.sdmitriy.firebasetestapp.util.Constants;
import com.sdmitriy.firebasetestapp.util.LocationHelper;

public class MapFragmentPresenter {

    private MapFragment fragment;
    private LocationHelper locationHelper;

    public MapFragmentPresenter(MapFragment mapFragment) {
        this.fragment = mapFragment;
        onMapFragmentOpenStrategy(fragment.getArguments());
    }

    private void onMapFragmentOpenStrategy(Bundle args) {
        if (args != null && args.getParcelable(Constants.BUNDLE_PLACE) != null) {
            //todo realization
        } else {
            initializeLocationHelper(fragment);
        }
    }

    private void initializeLocationHelper(MapFragment mapFragment) {
        if (mapFragment.getActivity() != null) {
            locationHelper = new LocationHelper(mapFragment.getActivity());
            locationHelper.connectGoogleApiClient();
        }
    }

    public void onRequestPermissionsResult(int resultCode, int[] grantResults) {
        locationHelper.onRequestPermissionResult(resultCode, grantResults);
    }

    public void disconnectGoogleApiClient() {
        locationHelper.disconnectGoogleApiClient();
    }
}
