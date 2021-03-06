package com.mrswimmer.galleryforyandexschool.presentation.splash.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mrswimmer.galleryforyandexschool.R;

import javax.inject.Inject;

import agency.tango.materialintroscreen.MaterialIntroActivity;

public class IntroActivity extends MaterialIntroActivity {

    @Inject
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] intros = getResources().getStringArray(R.array.intros);
        enableLastSlideAlphaExitTransition(true);
        for(int i=0; i<intros.length; i++) {
            addSlide(new IntroFragment(intros[i]));
        }
    }


    @Override
    public void onFinish() {
        super.onFinish();
    }
}