package com.faskn.app.weatherapp.di.module

import android.os.Environment
import com.algolia.search.saas.places.PlacesClient
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.faskn.app.weatherapp.core.Constants
import com.faskn.app.weatherapp.domain.DefaultRequestInterceptor
import com.faskn.app.weatherapp.domain.WeatherAppAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetModule {
    @Singleton
    @Provides
    @Named("cached")
    fun provideOkHttpClient(): OkHttpClient {
        val cache = Cache(Environment.getDownloadCacheDirectory(), 10 * 1024 * 1024)
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(DefaultRequestInterceptor())
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    @Named("non_cached")
    fun provideNonCachedOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(DefaultRequestInterceptor())
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, @Named("cached") client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit.Builder): WeatherAppAPI {
        return retrofit.baseUrl(Constants.NetworkService.BASE_URL)
            .build()
            .create(WeatherAppAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePlacesClient(): PlacesClient {
        return PlacesClient(Constants.AlgoliaKeys.APPLICATION_ID, Constants.AlgoliaKeys.SEARCH_API_KEY)
    }
}
