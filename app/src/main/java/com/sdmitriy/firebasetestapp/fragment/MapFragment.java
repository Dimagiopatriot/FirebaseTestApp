package com.sdmitriy.firebasetestapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.presenter.MapFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private Unbinder unbinder;
    private MapFragmentPresenter presenter;

    @BindView(R.id.map)
    MapView mapView;

    private GoogleMap map;

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

        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, grantResults);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        presenter.disconnectGoogleApiClient();
        super.onDestroyView();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
