package com.example.seconddaggerbuilderapp.di.modules

import com.example.seconddaggerbuilderapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeAuthActivity(): MainActivity

}