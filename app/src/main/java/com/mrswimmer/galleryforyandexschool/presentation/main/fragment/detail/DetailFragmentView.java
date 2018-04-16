package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.detail;

import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseView;

public interface DetailFragmentView extends BaseView {
    void initDetail(ImageItem imageItem, String mailKey);

    void setLikes(int count);
}
