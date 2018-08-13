package com.sdmitriy.firebasetestapp.fragment.secondpart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.adapter.secondpart.NewsFeedPagerAdapter;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.NewsFeedItem;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.ViewPagerAdapterItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dmitriysmishnyi on 13.08.18.
 */

public class NewsFeedFragment extends Fragment {

    Unbinder unbinder;

    @BindView(R.id.tab_dots)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfeed_part_two_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        NewsFeedPagerAdapter newsFeedPagerAdapter = new NewsFeedPagerAdapter(getContext(), getTopViewPagerItems());

        tabLayout.setupWithViewPager(viewPager, true);
        viewPager.setAdapter(newsFeedPagerAdapter);
        return view;
    }

    private List<ViewPagerAdapterItem> getTopViewPagerItems() {
        List<ViewPagerAdapterItem> items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            items.add(new NewsFeedItem());
        }
        return items;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
