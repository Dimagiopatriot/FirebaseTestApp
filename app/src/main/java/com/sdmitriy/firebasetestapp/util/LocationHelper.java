package com.sdmitriy.firebasetestapp.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.sdmitriy.firebasetestapp.presenter.MapFragmentPresenter;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class LocationHelper implements LocationListener {

    private final int REQUEST_PERMISSION_REQUEST_CODE = 34;
    private final int REQUEST_CHECK_SETTINGS = 1;

    private Location lastLocation;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    private Context context;
    private Activity activity;

    private MapFragmentPresenter presenter;

    public LocationHelper(Activity activity, MapFragmentPresenter presenter) {
        this.activity = activity;
        context = activity.getApplicationContext();
        this.presenter = presenter;

        initializeGoogleApiClient();
    }

    public void connectGoogleApiClient() {
        if (googleApiClient != null) {
            googleApiClient.connect();
        } else {
            Toast.makeText(context, "Google API client not connected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void disconnectGoogleApiClient() {
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }

    private void initializeGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        LocationSettingsRequest.Builder builder = getSettingsLocationRequest();
                        callbackPendingResult(builder);
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Toast.makeText(context, "Connection is suspend!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnConnectionFailedListener(this::onConnectionFailed)
                .addApi(LocationServices.API)
                .build();
    }

    private LocationSettingsRequest.Builder getSettingsLocationRequest() {
        long timeoutInterval = 10000; //in milisecs
        long fastestTimeoutInterval = 1000;

        locationRequest = new LocationRequest();
        locationRequest.setInterval(timeoutInterval);
        locationRequest.setFastestInterval(fastestTimeoutInterval);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
    }

    private void callbackPendingResult(LocationSettingsRequest.Builder builder) {
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                .checkLocationSettings(googleApiClient, builder.build());

        result.setResultCallback(locationSettingsResult -> {
            final Status status = locationSettingsResult.getStatus();
            switch (status.getStatusCode()) {
                case LocationSettingsStatusCodes.SUCCESS:
                    // All location settings are satisfied. The client can
                    // initialize location requests here.
                    if (!checkPermissions()) {
                        startLocationPermissionRequest();
                    } else {
                        checkGoogleClientConnection();
                        getLastLocation();
                    }
                    break;
                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        status.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException e) {
                        // Ignore the error.
                    }
                    break;
                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                    // Location settings are not satisfied. However, we have no way
                    // to fix the settings so we won't show the dialog.
                    break;
            }
        });
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(activity, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSION_REQUEST_CODE);
    }

    private boolean checkPermissions() {
        int fineLocationPermissionState = ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION);
        int coarseLocationPermissionState = ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION);
        return fineLocationPermissionState == PackageManager.PERMISSION_GRANTED && coarseLocationPermissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void checkGoogleClientConnection() {
        if (!googleApiClient.isConnected()) {
            googleApiClient.connect();
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        fusedLocationClient.getLastLocation().addOnCompleteListener(command -> {
            if (command.isSuccessful()) {
                lastLocation = command.getResult();
                presenter.moveCameraToUserPosition(lastLocation);
            } else {
                fusedLocationClient.requestLocationUpdates(locationRequest, null);
            }
        });
    }

    private void onConnectionFailed(ConnectionResult result) {
        Toast.makeText(context, "Connection failed!", Toast.LENGTH_SHORT).show();

        if (!result.hasResolution()) {
            Log.i("Current location", "Location services connection failed with code " + result.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
    }

    public void onRequestPermissionResult(int requestCode, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        checkGoogleClientConnection();
                        getLastLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to do
                        Toast.makeText(context, "Location Service not Enabled", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
        }
    }
}
