package com.mrswimmer.galleryforyandexschool.domain.service;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kelvinapps.rxfirebase.DataSnapshotMapper;
import com.kelvinapps.rxfirebase.RxFirebaseAuth;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.data.model.User;
import com.mrswimmer.galleryforyandexschool.data.settings.Settings;
import com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery.GalleryFragment;

import java.util.List;

public class FireService {
    private DatabaseReference reference;
    private FirebaseAuth auth;
    private StorageReference storageReference;

    public FireService() {
        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void signIn(String email, String password, AuthCallBack callBack) {
        RxFirebaseAuth.signInWithEmailAndPassword(auth, email, password)
                .map(authResult -> authResult.getUser() != null)
                .take(1)
                .subscribe(callBack::onSuccess, callBack::onError);
    }

    public void signUp(String email, String password, AuthCallBack callBack) {
        RxFirebaseAuth.createUserWithEmailAndPassword(auth, email, password)
                .map(authResult -> authResult.getUser() != null)
                .take(1)
                .subscribe(callBack::onSuccess, callBack::onError);
    }

    public boolean checkLogIn() {
        return null != auth.getCurrentUser();
    }

    public void addUser(String mailKey, User user) {
        DatabaseReference newUser = reference.child("users").child(mailKey);
        newUser.setValue(user);
    }
    public void getUser(String mail, UserCallback callback) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").child(mail), User.class)
                .subscribe(callback::onSuccess, callback::onError);
    }

    public void getGallery(String key, String mail, GalleryCallback callback) {
        switch (key) {
            case Settings.GALLERY_GALLERY:
                RxFirebaseDatabase.observeSingleValueEvent(reference.child("gallery"), DataSnapshotMapper.listOf(ImageItem.class))
                        .subscribe(callback::onSuccess, callback::onError);
                break;
            case Settings.GALLERY_FAVORITE:
                RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").child(mail).child("favorites"), DataSnapshotMapper.listOf(ImageItem.class))
                        .subscribe(callback::onSuccess, callback::onError);
                break;
            case Settings.GALLERY_MY_GALLERY:
                RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").child(mail).child("my"), DataSnapshotMapper.listOf(ImageItem.class))
                        .subscribe(callback::onSuccess, callback::onError);
                break;
        }
    }

    public void uploadImage(String imageName, Uri uriImage, UploadImageCallBack callback) {
        StorageReference shopsImages = storageReference.child(imageName);
        shopsImages.putFile(uriImage)
                .addOnSuccessListener(callback::onSuccess)
                .addOnFailureListener(callback::onError);
    }

    public void createImage(String mailKey, ImageItem imageItem) {
        DatabaseReference newImageGallery = reference.child("gallery").push();
        imageItem.setId(newImageGallery.getKey());
        newImageGallery.setValue(imageItem);
        DatabaseReference newImageMyGallery = reference.child(mailKey).child("my").child(imageItem.getId());
        newImageMyGallery.setValue(imageItem);

    }

    public interface UploadImageCallBack {
        void onSuccess(UploadTask.TaskSnapshot taskSnapshot);

        void onError(Throwable e);
    }

    public interface AuthCallBack {
        void onSuccess(boolean success);

        void onError(Throwable e);
    }

    public interface UserCallback {
        void onSuccess(User user);

        void onError(Throwable e);
    }

    public interface GalleryCallback {
        void onSuccess(List<ImageItem> imageItems);

        void onError(Throwable e);
    }
}