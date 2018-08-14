package com.sdmitriy.firebasetestapp.model.adapter.secondpart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdmitriy.firebasetestapp.R;
import com.sdmitriy.firebasetestapp.model.adapter.CommonRecyclerViewAdapter;
import com.sdmitriy.firebasetestapp.model.entity.secondpart.CategoryItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryRecyclerViewAdapter extends CommonRecyclerViewAdapter<CategoryItem, CategoryRecyclerViewAdapter.Holder> {

    private List<CategoryItem> items;

    CategoryRecyclerViewAdapter(List<CategoryItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        CategoryItem categoryItem = items.get(position);
        String likesCount = String.valueOf(categoryItem.getLikesCount()) + " Likes";
        String commentsCount = String.valueOf(categoryItem.getCommentsCount() + " Comments");

        holder.categoryItem = categoryItem;
        holder.likes.setText(likesCount);
        holder.comments.setText(commentsCount);
        holder.articleTitle.setText(categoryItem.getTitle());

        Picasso.get().load(categoryItem.getPictureResource()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void addItems(List<CategoryItem> items) {
        if (items.size() != this.items.size() || !this.items.containsAll(items)) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeItem(CategoryItem item) {
        items.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public void onDataChangedResponse() {
        //nothing to do
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.article_title)
        TextView articleTitle;
        @BindView(R.id.likes)
        TextView likes;
        @BindView(R.id.comments)
        TextView comments;

        CategoryItem categoryItem;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.ic_like)
        void changeLikeDrawable() {
            if (categoryItem.isLikedByUser()) {
                likes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_unlike, 0, 0, 0);
                categoryItem.setLikedByUser(false);
            } else {
                likes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_likes, 0, 0, 0);
                categoryItem.setLikedByUser(true);
            }
        }
    }
}
