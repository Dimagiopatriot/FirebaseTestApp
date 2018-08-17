package com.sdmitriy.firebasetestapp.model.adapter.secondpart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.adapter.CommonRecyclerViewAdapter;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.CarouselItem;

import java.util.List;

public class CarouselAdapter extends CommonRecyclerViewAdapter<CarouselItem, CarouselAdapter.Holder> {

    private List<CarouselItem> items;

    public CarouselAdapter(List<CarouselItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carousel_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        CarouselItem carouselItem = items.get(position);
        holder.day.setText(String.valueOf(carouselItem.getDay()));
        holder.month.setText(carouselItem.getMonth());
        holder.events.setText(carouselItem.getEventsCount());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void addItems(List<CarouselItem> items) {
        this.items.addAll(items);
    }

    @Override
    public void removeItem(CarouselItem item) {
        items.remove(item);
    }

    @Override
    public void onDataChangedResponse() {

    }

    class Holder extends RecyclerView.ViewHolder {

        TextView day, month, events;

        public Holder(View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.day);
            month = itemView.findViewById(R.id.month);
            events = itemView.findViewById(R.id.events);
        }
    }
}
