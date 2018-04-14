package com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_in;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.data.Screens;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Global;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Local;
import com.mrswimmer.galleryforyandexschool.domain.service.FireService;
import com.mrswimmer.galleryforyandexschool.domain.service.SettingsService;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SignInFragmentPresenter extends MvpPresenter<SignInFragmentView> {
    @Inject
    @Local
    Router router;

    @Inject
    @Global
    Router globalRouter;

    @Inject
    SettingsService settingsService;

    @Inject
    FireService fireService;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public SignInFragmentPresenter() {
        App.getComponent().inject(this);
    }

    void enter(String email, String pass) {
        fireService.signIn(email, pass, new FireService.AuthCallBack() {
            @Override
            public void onSuccess(boolean success) {
                settingsService.saveUsernameAndMail(email);
                globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showToast(e.getMessage());
            }
        });
    }

    void gotoReg() {
        //fireService.getProducts();
        /*DatabaseReference newProd = reference.child("products").push();
        ArrayList<String> images = new ArrayList<>();
        images.add("http://heaclub.ru/tim/673c999977399788744cde08181b449d.jpg");
        images.add("url2");
        ArrayList<Availability> availabilities = new ArrayList<>();
        availabilities.add(new Availability(2, "0123"));
        availabilities.add(new Availability(4, "456"));
        ArrayList<Review> reviews = new ArrayList<>();
        reviews.add(new Review("blablabla", 3, "rtr", "name1"));
        reviews.add(new Review("ok", 4, "lol", "name2"));
        newProd.setValue(new Product(newProd.getKey(), 100, "OK", "Шмаль", 199, images, availabilities, 1, 3, 5, -1, reviews));
        newProd = reference.child("products").push();
        newProd.setValue(new Product(newProd.getKey(), 150, "OK", "Волшебный чай", 99, images, availabilities, 1, 2, 4, -1, reviews));
        newProd = reference.child("products").push();
        newProd.setValue(new Product(newProd.getKey(), 200, "OK", "Ядреный кофе", 149, images, availabilities, 0, 1, 2, 99, reviews));
        newProd = reference.child("products").push();
        newProd.setValue(new Product(newProd.getKey(), 100, "OK", "эКспрессо", 499, images, availabilities, 0, 3, 3, -1,reviews));
        Log.i("code", "OK");*/
        /*DatabaseReference newShop = reference.child("shops").push();
        ArrayList<String> images = new ArrayList<>();
        images.add("http://tadviser.ru/images/8/8d/%D0%9C%D0%B0%D0%B3%D0%B0%D0%B7%D0%B8%D0%BD%D1%8B_%D1%87%D0%B0%D1%8F_%D0%B8_%D0%BA%D0%BE%D1%84%D0%B5.jpg");
        images.add("url2");
        ArrayList<Review> reviews = new ArrayList<>();
        reviews.add(new Review("blablabla", 3, "rtr", "name1"));
        reviews.add(new Review("ok", 4, "lol", "name2"));
        newShop.setValue(new Shop(newShop.getKey(), "Ленина 40", 8, 20, images, reviews, "Новосибирск", 4));
        newShop = reference.child("shops").push();
        reviews.add(new Review("ok", 4, "lol", "name2"));
        newShop.setValue(new Shop(newShop.getKey(), "Строителей 36", 9, 18, images, reviews, "Кемерово", 5));
        newShop = reference.child("shops").push();
        newShop.setValue(new Shop(newShop.getKey(), "Шарага 12", 10, 21, images, reviews, "Кемерово", 3));
        Log.i("code", "OK");*/
        router.navigateTo(Screens.SIGN_UP_SCREEN);
    }

    void goToMain() {
        SharedPreferences.Editor editor = settings.edit();
        globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
    }
}
