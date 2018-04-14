package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mrswimmer.galleryforyandexschool.R;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    ImageView image;

    public ImageViewHolder(View v) {
        super(v);
        image = v.findViewById(R.id.item_gallery_image);
    }
}
