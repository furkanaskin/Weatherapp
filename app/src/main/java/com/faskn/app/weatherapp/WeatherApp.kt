package com.faskn.app.weatherapp

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.faskn.app.weatherapp.di.component.DaggerApplicationComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import timber.log.Timber

class WeatherApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .applicationBind(this)
            .build()
            .inject(this)

        Stetho.initializeWithDefaults(this)
        Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)
    }
}
