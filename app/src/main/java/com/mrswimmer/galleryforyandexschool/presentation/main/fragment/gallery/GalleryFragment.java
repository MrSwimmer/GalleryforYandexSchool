package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.data.settings.Settings;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseFragment;
import com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery.recycler.ImagesAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GalleryFragment extends BaseFragment implements GalleryFragmentView {
    @InjectPresenter
    GalleryFragmentPresenter presenter;

    @ProvidePresenter
    public GalleryFragmentPresenter presenter() {
        return new GalleryFragmentPresenter();
    }

    @BindView(R.id.gallery_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.gallery_add_button)
    FloatingActionButton addButton;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = this.getArguments();
        String key = Settings.GALLERY_GALLERY;
        if (bundle != null) {
            key = bundle.getString(Settings.GALLERY_BUNDLE_KEY);
        }
        presenter.setRecyclerData(key);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_gallery;
    }

    @Override
    public void initAdapter(ArrayList<ImageItem> imageItems) {
        recyclerView.setAdapter(new ImagesAdapter(imageItems, getActivity()));
    }

    @OnClick(R.id.gallery_add_button)
    public void onAddButtonClick() {
        presenter.gotoNewImage();
    }
}
