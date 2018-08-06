package com.sdmitriy.firebasetestapp.model.adapter;

import android.support.v7.widget.RecyclerView;

import com.sdmitriy.firebasetestapp.model.entity.Place;

import java.util.List;

public abstract class CommonRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements Adapter<T>{
}
