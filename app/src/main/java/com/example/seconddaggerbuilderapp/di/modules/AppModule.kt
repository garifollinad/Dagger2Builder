package com.example.seconddaggerbuilderapp.di.modules

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.example.seconddaggerbuilderapp.api.PostApi
import com.example.seconddaggerbuilderapp.database.Database
import com.example.seconddaggerbuilderapp.repository.PostDaoRepository
import com.example.seconddaggerbuilderapp.repository.PostDaoRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule constructor(private val context: Context){

    @Provides
    @Singleton
    internal fun provideDatabase() = Room.databaseBuilder(
        context,
        Database::class.java,
        "database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    internal fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    internal fun provideWorkerManger() = WorkManager.getInstance()

    @Provides
    @Singleton
    internal fun providePostDaoRepository(
       database: Database,
       api: PostApi
    ): PostDaoRepository = PostDaoRepositoryImpl(database, api)

}