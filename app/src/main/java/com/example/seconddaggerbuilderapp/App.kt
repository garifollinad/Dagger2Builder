package com.example.seconddaggerbuilderapp

import android.app.Activity
import android.app.Application
import android.webkit.WebView
import androidx.work.WorkManager
import com.example.seconddaggerbuilderapp.di.components.DaggerAppComponent
import com.example.seconddaggerbuilderapp.di.modules.AppModule
import com.example.seconddaggerbuilderapp.di.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kz.mobile.mgov.common.di.ActivityLifecycleHelper
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    internal var currentActivity: Activity? = null

    private val appComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .networkModule(NetworkModule(this))
            .build()
    }

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        initWorkManager()
        ActivityLifecycleHelper.init(this)
    }

    private fun initWorkManager() {
        WorkManager.initialize(this, androidx.work.Configuration.Builder().run {
            setWorkerFactory(appComponent.daggerWorkerFactory())
            build()
        })
    }

}