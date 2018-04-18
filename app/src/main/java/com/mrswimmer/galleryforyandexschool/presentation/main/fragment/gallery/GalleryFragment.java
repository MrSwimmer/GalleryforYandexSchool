package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.florent37.tutoshowcase.TutoShowcase;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.data.settings.Settings;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseFragment;
import com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery.recycler.ImagesAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GalleryFragment extends BaseFragment implements GalleryFragmentView, SwipeRefreshLayout.OnRefreshListener {
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
    @BindView(R.id.gallery_swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.gallery_empty_text)
    TextView emptyText;

    String key = Settings.GALLERY_GALLERY;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        Bundle bundle = this.getArguments();
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
        swipe.setRefreshing(false);
        if (imageItems.size() == 0)
            emptyText.setVisibility(View.VISIBLE);
        else
            emptyText.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(new ImagesAdapter(imageItems, getActivity()));
    }

    @OnClick(R.id.gallery_add_button)
    public void onAddButtonClick() {
        presenter.gotoNewImage();
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        presenter.setRecyclerData(key);
    }
}
