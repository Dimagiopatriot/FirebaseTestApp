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
import com.sdmitriy.firebasetestapp.model.adapter.secondpart.CategoryPagerAdapter;
import com.sdmitriy.firebasetestapp.model.adapter.secondpart.HotNewsFeedPagerAdapter;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.CategoryItem;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.CategoryTabItem;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.HotNewsTabItem;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.ViewPagerAdapterItem;
import com.sdmitriy.firebasetestapp.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dmitriysmishnyi on 13.08.18.
 */

public class NewsFeedFragment extends Fragment {

    private final int TOP_NEWS_NUMBER = 5;
    private final int CATEGORIES_NUMBER = 4;

    Unbinder unbinder;

    @BindView(R.id.tab_dots)
    TabLayout dotsLayout;
    @BindView(R.id.pager)
    ViewPager hotNewsViewPager;

    @BindView(R.id.category_tabs)
    TabLayout categoryTabs;
    @BindView(R.id.category_view_pager)
    ViewPager categoryViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfeed_part_two_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        initHotNews();
        initCategories();
        return view;
    }

    private void initHotNews() {
        HotNewsFeedPagerAdapter hotNewsFeedAdapter = new HotNewsFeedPagerAdapter(getContext(), getTopViewPagerItems());

        dotsLayout.setupWithViewPager(hotNewsViewPager, true);
        hotNewsViewPager.setAdapter(hotNewsFeedAdapter);
    }

    private void initCategories() {
        CategoryPagerAdapter categoryPagerAdapter = new CategoryPagerAdapter(getContext(), getCategoryPagerItems());

        categoryTabs.setupWithViewPager(categoryViewPager, true);
        categoryViewPager.setAdapter(categoryPagerAdapter);
    }

    private List<ViewPagerAdapterItem> getTopViewPagerItems() {
        List<ViewPagerAdapterItem> items = new ArrayList<>();
        for (int i = 0; i < TOP_NEWS_NUMBER; i++) {
            items.add(new HotNewsTabItem());
        }
        return items;
    }

    private List<ViewPagerAdapterItem> getCategoryPagerItems() {
        List<ViewPagerAdapterItem> items = new ArrayList<>();
        for (int i = 0; i < CATEGORIES_NUMBER; i++) {
            items.add(new CategoryTabItem());
        }
        return items;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
