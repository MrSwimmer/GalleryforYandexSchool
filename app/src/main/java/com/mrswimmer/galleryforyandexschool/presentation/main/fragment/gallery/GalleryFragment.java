package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseFragment;

import butterknife.BindView;

public class GalleryFragment extends BaseFragment implements GalleryFragmentView {
    @InjectPresenter
    GalleryFragmentPresenter presenter;

    @ProvidePresenter
    public GalleryFragmentPresenter presenter() {
        return new GalleryFragmentPresenter();
    }

    @BindView(R.id.gallery_recycler)
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        presenter.setRecyclerData();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_gallery;
    }
}
