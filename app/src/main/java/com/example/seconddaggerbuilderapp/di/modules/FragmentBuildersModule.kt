package com.example.seconddaggerbuilderapp.di.modules

import com.example.seconddaggerbuilderapp.ui.PostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostFragment


}