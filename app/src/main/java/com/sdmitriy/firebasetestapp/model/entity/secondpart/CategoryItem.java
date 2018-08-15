package com.sdmitriy.firebasetestapp.model.entity.secondpart;

public class CategoryItem {

    private int pictureResource;
    private String title;
    private int likesCount;
    private int commentsCount;
    private boolean isLikedByUser;

    public CategoryItem(int pictureResource, String title, int likesCount, int commentsCount, boolean isLikedByUser) {
        this.pictureResource = pictureResource;
        this.title = title;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.isLikedByUser = isLikedByUser;
    }

    public int getPictureResource() {
        return pictureResource;
    }

    public String getTitle() {
        return title;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public boolean isLikedByUser() {
        return isLikedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        isLikedByUser = likedByUser;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }
}
