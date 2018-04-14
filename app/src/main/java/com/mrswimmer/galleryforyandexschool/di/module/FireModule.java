package com.mrswimmer.galleryforyandexschool.di.module;


import com.mrswimmer.galleryforyandexschool.domain.service.FireService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FireModule {
    @Provides
    @Singleton
    FireService providesService() {
        return new FireService();
    }
}
