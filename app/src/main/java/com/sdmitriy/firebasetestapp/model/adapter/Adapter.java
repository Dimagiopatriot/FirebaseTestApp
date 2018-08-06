package com.sdmitriy.firebasetestapp.model.adapter;

import com.sdmitriy.firebasetestapp.model.entity.Place;

import java.util.List;

/**
 * Created by dmitriysmishnyi on 06.08.18.
 */

public interface Adapter<T> {

    void addItems(List<T> items);

    void removeItem(Place item);
}
