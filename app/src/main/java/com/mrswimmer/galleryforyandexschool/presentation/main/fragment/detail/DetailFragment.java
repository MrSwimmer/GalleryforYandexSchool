package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.data.settings.Settings;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailFragment extends BaseFragment implements DetailFragmentView {
    @InjectPresenter
    DetailFragmentPresenter presenter;

    @ProvidePresenter
    public DetailFragmentPresenter presenter() {
        return new DetailFragmentPresenter();
    }

    @BindView(R.id.detail_image)
    ImageView image;
    @BindView(R.id.detail_like_button)
    ImageView likeButton;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.detail_description)
    TextView description;
    @BindView(R.id.detail_likes)
    TextView likes;

    float alphaLike = (float) 0.2;
    boolean isLike = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        String id = null;
        if (bundle != null) {
            id = bundle.getString(Settings.DETAIL_BUNDLE_ID);
        }
        presenter.setImageDetail(id);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_detail;
    }

    @Override
    public void initDetail(ImageItem imageItem, String mailKey) {
        Glide.with(getActivity())
                .load(imageItem.getUrl())
                .placeholder(R.color.black)
                .into(image);
        name.setText(imageItem.getTitle());
        description.setText(imageItem.getDescription());
        likes.setText(imageItem.getLikes().size() + "");
        if (imageItem.getLikes().contains(mailKey)) {
            isLike = true;
        }
        setAlphaLike();
    }

    @OnClick(R.id.detail_like_button)
    void OnLikeClickListener() {
        isLike = !isLike;
        /*presenter.*/
        setAlphaLike();
    }

    private void setAlphaLike() {
        if (isLike) {
            likeButton.setAlpha(1);
        } else {
            likeButton.setAlpha(alphaLike);
        }
    }

}
