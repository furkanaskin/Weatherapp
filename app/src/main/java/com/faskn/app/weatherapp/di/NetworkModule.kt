package com.faskn.app.weatherapp.di

import android.os.Environment
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.faskn.app.weatherapp.core.Constants
import com.faskn.app.weatherapp.domain.OWMRequestInterceptor
import com.faskn.app.weatherapp.domain.PKRequestInterceptor
import com.faskn.app.weatherapp.domain.WeatherAppAPI
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideCache(): Cache =
        Cache(Environment.getDownloadCacheDirectory(), 10 * 1024 * 1024)

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)

    @Provides
    @Singleton
    @Named("RetrofitOWM")
    fun provideRetrofitForOpenWeatherMap(
        moshi: Moshi,
        okHttpClientBuilder: OkHttpClient.Builder,
        cache: Cache,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.NetworkService.OWM_BASE_URL)
        .client(
            okHttpClientBuilder.addInterceptor(OWMRequestInterceptor()).cache(cache).build()
        )
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    @Named("RetrofitPK")
    fun provideRetrofitForPlaceKit(
        moshi: Moshi,
        okHttpClientBuilder: OkHttpClient.Builder,
        cache: Cache,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.NetworkService.PK_BASE_URL)
        .client(
            okHttpClientBuilder.addInterceptor(PKRequestInterceptor()).cache(cache).build()
        )
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    @Named("OwmService")
    fun provideOwmService(@Named("RetrofitOWM") retrofit: Retrofit): WeatherAppAPI =
        retrofit.create(WeatherAppAPI::class.java)

    @Provides
    @Singleton
    @Named("PkService")
    fun providePkService(@Named("RetrofitPK") retrofit: Retrofit): WeatherAppAPI =
        retrofit.create(WeatherAppAPI::class.java)
}