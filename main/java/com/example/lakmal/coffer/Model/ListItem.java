package com.example.lakmal.coffer.Model;

public class ListItem {
    private int imageResId;
    private String subTitle;
    private String title;
    private String detail;
    private String off_percent;

    private boolean favourite = false;

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOff_percent() {
        return off_percent;
    }

    public void setOff_percent(String off_percent) {
        this.off_percent = off_percent;
    }
}