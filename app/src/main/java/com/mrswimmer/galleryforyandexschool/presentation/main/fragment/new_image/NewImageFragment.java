package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.new_image;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.data.settings.Settings;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class NewImageFragment extends BaseFragment implements NewImageFragmentView {
    @InjectPresenter
    NewImageFragmentPresenter presenter;

    @ProvidePresenter
    public NewImageFragmentPresenter presenter() {
        return new NewImageFragmentPresenter();
    }

    @BindView(R.id.new_image_image)
    ImageView image;
    @BindView(R.id.new_image_name)
    EditText editName;
    @BindView(R.id.new_image_description)
    EditText editDescription;
    @BindView(R.id.new_image_upload)
    Button upload;

    boolean setImage = false;
    String description, name;
    Uri uriImage = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_new_image;
    }

    @OnClick(R.id.new_image_upload)
    public void onUploadClickListener() {
        name = editName.getText().toString();
        description = editDescription.getText().toString();
        if (checkOnFillingFields()) {
            ImageItem imageItem = new ImageItem(name, description);
            presenter.createImage(imageItem, uriImage);
        }
    }

    boolean checkOnFillingFields() {
        if (name.equals("") || description.equals("") || uriImage.equals(null))
            return false;
        return true;
    }

    @OnClick(R.id.new_image_image)
    public void onImageClickListener() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Settings.GALLERY_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case Settings.GALLERY_REQUEST:
                Glide.with(getActivity())
                        .load(imageReturnedIntent.getData())
                        .placeholder(R.color.black)
                        .into(image);
                /*if (resultCode == RESULT_OK) {
                    uriImage = imageReturnedIntent.getData();
                    image.setImageURI(uriImage);
                } else {
                    Log.i("code", "error show large photo " + resultCode);
                    Glide.with(getActivity())
                            .load(imageReturnedIntent.getData())
                            .placeholder(R.color.black)
                            .into(image);
                }*/
        }
    }
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.undo:
                Toast.makeText(getActivity(), "onBack", Toast.LENGTH_SHORT).show();
                presenter.goBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
