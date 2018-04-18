package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.detail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Local;
import com.mrswimmer.galleryforyandexschool.domain.service.FireService;
import com.mrswimmer.galleryforyandexschool.domain.service.SettingsService;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class DetailFragmentPresenter extends MvpPresenter<DetailFragmentView> {
    @Inject
    @Local
    Router router;
    @Inject
    FireService fireService;
    @Inject
    SettingsService settingsService;

    ImageItem currentImage;

    public DetailFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setImageDetail(String id) {
        fireService.getImage(id, new FireService.ImageDetailCallback() {
            @Override
            public void onSuccess(ImageItem imageItem) {
                currentImage = imageItem;
                getViewState().initDetail(imageItem, settingsService.getMailKey());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", "error detail " + e.getMessage());
            }
        });
    }

    public void setLike(boolean isLike) {
        if (isLike)
            fireService.like(currentImage, settingsService.getMailKey(), new FireService.ImageDetailCallback() {
                @Override
                public void onSuccess(ImageItem imageItem) {
                    getViewState().setLikes(imageItem.getLikes().size());
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        else
            fireService.disLike(currentImage, settingsService.getMailKey(), new FireService.ImageDetailCallback() {
                @Override
                public void onSuccess(ImageItem imageItem) {
                    getViewState().setLikes(imageItem.getLikes().size());
                }

                @Override
                public void onError(Throwable e) {
                    getViewState().showToast(e.getMessage());
                }
            });
    }

    public void download() {

    }

    String getNameImageByUrl(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }


    public Target initTarget(String url) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(() -> {
                    File file = new File(Environment.getExternalStorageDirectory() + "/GalleryforYandexSchool/" + getNameImageByUrl(url));
                    try {
                        file.createNewFile();
                        FileOutputStream ostream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                        ostream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
                getViewState().showToast("Картинка успешно загружена в папку приложения");
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                getViewState().showToast("Ошибка загрузки");
                getViewState().tryDownloadAgain();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {
                }
            }
        };
        return target;
    }

}
