package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.data.settings.Settings;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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
    @BindView(R.id.detail_download)
    ImageView download;
    @BindView(R.id.detail_description)
    TextView description;
    @BindView(R.id.detail_likes)
    TextView likes;

    float alphaLike = (float) 0.2;
    boolean isLike = false;
    String url;
    private Target target;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        //target = presenter.initTarget(url);
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
                .placeholder(R.color.transparent)
                .into(image);
        name.setText(imageItem.getTitle());
        description.setText(imageItem.getDescription());
        likes.setText(imageItem.getLikes().size() + "");
        url =imageItem.getUrl();
        target = presenter.initTarget(url);
        if (imageItem.getLikes().containsKey(mailKey)) {
            isLike = true;
        }
        setAlphaLike();
    }

    @Override
    public void setLikes(int count) {
        likes.setText(count+"");
    }

    @Override
    public void tryDownloadAgain() {
        download.setAlpha((float) 1);
        download.setClickable(true);
    }

    @OnClick(R.id.detail_like_button)
    void OnLikeClickListener() {
        isLike = !isLike;
        presenter.setLike(isLike);
        setAlphaLike();
    }

    private void setAlphaLike() {
        if (isLike) {
            likeButton.setAlpha((float) 1);
        } else {
            likeButton.setAlpha(alphaLike);
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_detail_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Изображение из Галереи " + url);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Поделиться");
                intent = Intent.createChooser(intent, "С помощью");
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }
    @OnClick(R.id.detail_download)
    void onDownloadClickListener() {
        download.setAlpha(alphaLike);
        download.setClickable(false);
        Picasso.with(getActivity()).load(url).into(target);
    }
}
