package com.sdmitriy.firebasetestapp.model.adapter.secondpart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.adapter.CommonRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class TimelinePhotosItemAdapter extends CommonRecyclerViewAdapter<Integer, TimelinePhotosItemAdapter.Holder> {

    private List<Integer> photos;

    public TimelinePhotosItemAdapter(@NonNull List<Integer> photos) {
        if (photos != null) {
            this.photos = photos;
        } else {
            this.photos = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_timeline_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Integer currentPhoto = photos.get(position);

        holder.photo.setImageResource(currentPhoto);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    @Override
    public void addItems(List<Integer> items) {
        photos.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void removeItem(Integer item) {
        photos.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public void onDataChangedResponse() {

    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView photo;

        public Holder(View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.timeline_image);
        }
    }
}
