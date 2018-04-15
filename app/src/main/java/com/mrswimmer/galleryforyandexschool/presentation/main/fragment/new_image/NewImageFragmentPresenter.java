package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.new_image;

import android.net.Uri;
import android.transition.Scene;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.storage.UploadTask;
import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.data.settings.Screens;
import com.mrswimmer.galleryforyandexschool.data.settings.Settings;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Local;
import com.mrswimmer.galleryforyandexschool.domain.service.FireService;
import com.mrswimmer.galleryforyandexschool.domain.service.SettingsService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class NewImageFragmentPresenter extends MvpPresenter<NewImageFragmentView> {
    @Inject
    @Local
    Router router;
    @Inject
    FireService fireService;
    @Inject
    SettingsService settingsService;

    public NewImageFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void createImage(ImageItem imageItem, Uri uriImage) {
        String imageName = "images/" + settingsService.getMailKey() + imageItem.getTitle() + ".jpg";
        fireService.uploadImage(imageName, uriImage, new FireService.UploadImageCallBack() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                imageItem.setUrl(String.valueOf(downloadUrl));
                fireService.createImage(settingsService.getMailKey(), imageItem);
                router.backTo(Screens.GALLERY_SCREEN);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", "in create err " + e.getMessage());
            }
        });
    }
}
