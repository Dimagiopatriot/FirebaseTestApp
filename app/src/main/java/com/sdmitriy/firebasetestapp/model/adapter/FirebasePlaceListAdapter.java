package com.sdmitriy.firebasetestapp.model.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.entity.Place;
import com.sdmitriy.firebasetestapp.presenter.PlacesListFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class FirebasePlaceListAdapter extends CommonRecyclerViewAdapter<Place, FirebasePlaceListAdapter.Holder> {

    private List<Place> items = new ArrayList<>();
    private PlacesListFragmentPresenter presenter;

    public FirebasePlaceListAdapter(PlacesListFragmentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addItems(List<Place> items) {
        if (items.size() != this.items.size() || !this.items.containsAll(items)) {
            this.items = items;
            notifyDataSetChanged();
        }
        presenter.checkListEmptiness();
    }

    @Override
    public void removeItem(Place place) {
        items.remove(place);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Place currentPlace = items.get(position);
        holder.place = currentPlace;
        holder.placeName.setText(currentPlace.getPlaceName());
        holder.placeLatitude.setText(String.valueOf(currentPlace.getLatitude()));
        holder.placeLongitude.setText(String.valueOf(currentPlace.getLongitude()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.place_name)
        TextView placeName;

        @BindView(R.id.place_latitude)
        TextView placeLatitude;

        @BindView(R.id.place_longitude)
        TextView placeLongitude;

        Place place;

        Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item)
        void onItemClick() {
            presenter.openMapFragment(place);
        }

        @OnLongClick(R.id.item)
        boolean onItemLongClick() {
            presenter.removePlace(place);
            return false;
        }
    }
}
