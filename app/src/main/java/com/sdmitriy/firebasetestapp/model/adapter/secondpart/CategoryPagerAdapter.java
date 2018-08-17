package com.sdmitriy.firebasetestapp.model.adapter.secondpart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.ViewPagerAdapterItem;
import com.sdmitriy.firebasetestapp.util.Utils;

import java.util.List;

public class CategoryPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ViewPagerAdapterItem> items;


    public CategoryPagerAdapter(Context context, List<ViewPagerAdapterItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewPagerAdapterItem item = items.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(item.getLayoutResId(), container, false);
        initRecyclerView(layout);
        container.addView(layout);
        return layout;
    }

    private void initRecyclerView(ViewGroup layout) {
        RecyclerView tabRecyclerView = layout.findViewById(R.id.category_recycler_view);
        CategoryRecyclerViewAdapter recyclerViewAdapter = new CategoryRecyclerViewAdapter(Utils.getCategoryItemListForRecyclerView());
        tabRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        tabRecyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Featured";
            case 2:
                return "Popular";
            case 3:
                return "My Favorites";
        }
        return null;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
