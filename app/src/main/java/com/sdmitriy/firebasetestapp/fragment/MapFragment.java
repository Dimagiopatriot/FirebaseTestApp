package com.sdmitriy.firebasetestapp.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.adapter.MapAdapter;
import com.sdmitriy.firebasetestapp.presenter.MapFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener {

    private Unbinder unbinder;
    private MapFragmentPresenter presenter;

    @BindView(R.id.map)
    MapView mapView;

    public static MapFragment getInstance(Bundle args) {
        MapFragment instance = new MapFragment();
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new MapFragmentPresenter(this);
        if (getActivity() != null) {
            this.getActivity().setTitle(R.string.title_map);
        }

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, grantResults);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        presenter.disconnectGoogleApiClient();
        super.onDestroyView();
    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        presenter.setMapAdapter(new MapAdapter(googleMap, presenter));
        presenter.onGoogleMapReady();

        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_save_place, null);
        EditText placeName = dialogView.findViewById(R.id.place_name);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .setTitle("Enter place name")
                .setPositiveButton("OK", (dialog, which) ->
                        presenter.savePlaceToDatabase(latLng, placeName.getText().toString()))
                .setNegativeButton("Cancel", (dialog, which) -> {});
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
        presenter.resetMarkerId();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        presenter.resetMarkerId();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
