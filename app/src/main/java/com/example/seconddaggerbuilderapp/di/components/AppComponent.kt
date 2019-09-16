package com.example.seconddaggerbuilderapp.di.components

import android.app.Application
import com.example.seconddaggerbuilderapp.App
import com.example.seconddaggerbuilderapp.di.factories.DaggerWorkerFactory
import com.example.seconddaggerbuilderapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        FragmentBuildersModule::class,
        ActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    fun daggerWorkerFactory(): DaggerWorkerFactory

    fun workerSubcomponentBuilder(): WorkerSubcomponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(appModule: AppModule): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }
}