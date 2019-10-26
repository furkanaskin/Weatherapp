package com.faskn.app.weatherapp

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.faskn.app.weatherapp.di.AppInjector
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import timber.log.Timber

class WeatherApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppInjector.init(this)
        Stetho.initializeWithDefaults(this)
        AndroidThreeTen.init(this)
    }
}
