package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery;

import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseView;

import java.util.ArrayList;

public interface GalleryFragmentView extends BaseView {
    void initAdapter(ArrayList<ImageItem> imageItems);
}
