package com.sdmitriy.firebasetestapp.util;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.sdmitriy.firebasetestapp.model.entity.MarkerItem;
import com.sdmitriy.firebasetestapp.presenter.MapFragmentPresenter;

public class CustomClusterRenderer extends DefaultClusterRenderer<MarkerItem> {

    private MapFragmentPresenter presenter;

    public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<MarkerItem> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<MarkerItem> cluster) {
        return cluster.getSize() > 2;
    }

    @Override
    protected void onClusterItemRendered(MarkerItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
        presenter.showConcretePlaceInfoWindow(clusterItem, marker);
    }

    @Override
    protected void onBeforeClusterItemRendered(MarkerItem item, MarkerOptions markerOptions) {
        markerOptions.icon(item.getMarker().getIcon());
    }

    public void setPresenter(MapFragmentPresenter presenter) {
        this.presenter = presenter;
    }
}
