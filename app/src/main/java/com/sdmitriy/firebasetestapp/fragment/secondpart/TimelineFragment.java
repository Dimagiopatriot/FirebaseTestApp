package com.sdmitriy.firebasetestapp.fragment.secondpart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.adapter.secondpart.CarouselAdapter;
import com.sdmitriy.firebasetestapp.model.adapter.secondpart.TimelineAdapter;
import com.sdmitriy.firebasetestapp.util.CenterZoomLayoutManager;
import com.sdmitriy.firebasetestapp.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TimelineFragment extends Fragment {

    Unbinder unbinder;

    @BindView(R.id.timeline_events)
    RecyclerView timelineEvents;

    @BindView(R.id.carousel_view)
    RecyclerView calendarCarouselView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timeline_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        timelineEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        timelineEvents.setAdapter(new TimelineAdapter(Utils.getTimelineEvents()));

        calendarCarouselView.setLayoutManager(new CenterZoomLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        calendarCarouselView.setAdapter(new CarouselAdapter(Utils.getCarouselItems()));
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.getItem(0).setVisible(true);
        menu.getItem(1).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
