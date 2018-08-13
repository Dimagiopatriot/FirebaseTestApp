package com.sdmitriy.firebasetestapp.model.entity.secondpart;

import com.sdmitriy.firebasetestapp.R;

/**
 * Created by dmitriysmishnyi on 13.08.18.
 */

public class NewsFeedItem implements ViewPagerAdapterItem {
    @Override
    public int getLayoutResId() {
        return R.layout.view_pager_feed;
    }
}
