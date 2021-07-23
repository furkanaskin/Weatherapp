package com.faskn.app.weatherapp.di

import android.os.Environment
import com.algolia.search.saas.places.PlacesClient
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.faskn.app.weatherapp.core.Constants
import com.faskn.app.weatherapp.domain.DefaultRequestInterceptor
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
    @Named("cached")
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(DefaultRequestInterceptor())
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .cache(Cache(Environment.getDownloadCacheDirectory(), 10 * 1024 * 1024))
            .build()

    @Provides
    @Singleton
    @Named("non_cached")
    fun provideNonCachedOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(DefaultRequestInterceptor())
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
        @Named("cached") client: OkHttpClient,
    ): Retrofit.Builder = Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit.Builder): WeatherAppAPI =
        retrofit.baseUrl(Constants.NetworkService.BASE_URL)
            .build()
            .create(WeatherAppAPI::class.java)

    @Provides
    @Singleton
    fun providePlacesClient(): PlacesClient =
        PlacesClient(
            Constants.AlgoliaKeys.APPLICATION_ID,
            Constants.AlgoliaKeys.SEARCH_API_KEY
        )

}