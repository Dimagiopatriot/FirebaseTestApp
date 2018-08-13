package com.sdmitriy.firebasetestapp.model.adapter.secondpart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdmitriy.firebasetestapp.model.entity.secondpart.ViewPagerAdapterItem;

import java.util.List;

/**
 * Created by dmitriysmishnyi on 13.08.18.
 */

public class NewsFeedPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ViewPagerAdapterItem> items;

    public NewsFeedPagerAdapter(Context context, List<ViewPagerAdapterItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewPagerAdapterItem item = items.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(item.getLayoutResId(), container, false);
        container.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setItems(List<ViewPagerAdapterItem> items) {
        this.items = items;
    }
}
