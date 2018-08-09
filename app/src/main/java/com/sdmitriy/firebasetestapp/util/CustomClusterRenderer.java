package com.sdmitriy.firebasetestapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.entity.MarkerItem;
import com.sdmitriy.firebasetestapp.presenter.MapFragmentPresenter;

public class CustomClusterRenderer extends DefaultClusterRenderer<MarkerItem> {

    private MapFragmentPresenter presenter;
    private final IconGenerator clusterIconGenerator;
    private Context context;

    public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<MarkerItem> clusterManager) {
        super(context, map, clusterManager);
        clusterIconGenerator = new IconGenerator(context);
        this.context = context;
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
    protected void onBeforeClusterRendered(Cluster<MarkerItem> cluster, MarkerOptions markerOptions) {
        clusterIconGenerator.setBackground(
                ContextCompat.getDrawable(context, R.drawable.cluster));
        clusterIconGenerator.setTextAppearance(R.style.AppTheme_WhiteTextAppearance);
        final Bitmap icon = clusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }

    @Override
    protected void onBeforeClusterItemRendered(MarkerItem item, MarkerOptions markerOptions) {
        markerOptions.icon(item.getMarker().getIcon());
    }

    public void setPresenter(MapFragmentPresenter presenter) {
        this.presenter = presenter;
    }
}
