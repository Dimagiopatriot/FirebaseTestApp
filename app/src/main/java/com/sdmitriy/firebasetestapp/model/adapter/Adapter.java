package com.sdmitriy.firebasetestapp.model.adapter;

import java.util.List;

/**
 * Created by dmitriysmishnyi on 06.08.18.
 */

public interface Adapter<T> {

    void addItems(List<T> items);

    void removeItem(T item);

    void onDataChangedResponse();
}
