package com.example.seconddaggerbuilderapp.di.modules

import android.content.Context
import com.example.seconddaggerbuilderapp.App
import com.example.seconddaggerbuilderapp.BuildConfig
import com.example.seconddaggerbuilderapp.api.PostApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedInputStream
import java.io.IOException
import java.security.*
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.*

private const val DAGGER_URL = "https://jsonplaceholder.typicode.com/"

@Module
class NetworkModule @Inject constructor(private val app: App) {


    @Singleton
    @Provides
    internal fun provideDaggerUrl(): String = DAGGER_URL


    @Singleton
    @Provides
    internal fun providePostApi(
        retrofit: Retrofit
    ): PostApi = retrofit.create(PostApi::class.java)

    @Singleton
    @Provides
    internal fun provideRetrofit(
        baseUrl: String,
        okHttpBuilder: OkHttpClient.Builder
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpBuilder.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    @Singleton
    @Provides
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return interceptor
    }


    @Singleton
    @Provides
    internal fun provideOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .followSslRedirects(false)
        //.connectionSpecs(Collections.singletonList(spec))
        if (BuildConfig.DEBUG) {
        }
        return builder
    }

}