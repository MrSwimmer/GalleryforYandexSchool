package com.mrswimmer.galleryforyandexschool.data.model;

public class ImageItem {
    String url;
    String title;
    String description;
    String id;

    public ImageItem(String url, String title, String description) {
        this.url = url;
        this.title = title;
        this.description = description;
    }

    public ImageItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ImageItem(String url, String title, String description, String id) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public ImageItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
