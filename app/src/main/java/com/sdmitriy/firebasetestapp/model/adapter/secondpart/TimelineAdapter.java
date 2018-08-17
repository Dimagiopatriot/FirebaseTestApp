package com.sdmitriy.firebasetestapp.model.adapter.secondpart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.adapter.CommonRecyclerViewAdapter;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.TimelineItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimelineAdapter extends CommonRecyclerViewAdapter<TimelineItem, TimelineAdapter.Holder> {

    private List<TimelineItem> items;
    private Context context;

    public TimelineAdapter(List<TimelineItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_item, parent, false);
        context = parent.getContext();
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        TimelineItem item = items.get(position);
        if (item.isGreen()) {
            holder.status.setImageResource(R.drawable.timeline_green_status);
        } else {
            holder.status.setImageResource(R.drawable.timeline_red_status);
        }

        holder.afternoon.setText(item.getAfternoon());
        holder.eventTime.setText(item.getEventTime());
        holder.eventTitle.setText(item.getEventTitle());
        holder.placeEvent.setText(item.getEventPlace());

        TimelinePhotosItemAdapter photosItemAdapter = new TimelinePhotosItemAdapter(item.getImagesDrawablesResources());
        holder.photos.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.photos.setAdapter(photosItemAdapter);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void addItems(List<TimelineItem> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void removeItem(TimelineItem item) {
        items.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public void onDataChangedResponse() {
        //nothing to do
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.event_status)
        ImageView status;
        @BindView(R.id.time_event)
        TextView eventTime;
        @BindView(R.id.afternoon)
        TextView afternoon;
        @BindView(R.id.event_title)
        TextView eventTitle;
        @BindView(R.id.place_event)
        TextView placeEvent;
        @BindView(R.id.photos)
        RecyclerView photos;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
