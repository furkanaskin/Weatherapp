package com.faskn.app.weatherapp.di.module

import com.faskn.app.weatherapp.di.scope.PerActivity
import com.faskn.app.weatherapp.ui.main.MainActivity
import com.faskn.app.weatherapp.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Furkan on 2019-10-16
 */


@Module
abstract class ActivityModule {

    /**
     * Injects [SplashActivity]
     *
     * @return an instance of [SplashActivity]
     */

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun splashActivity(): SplashActivity

    /**
     * Injects [MainActivity]
     *
     * @return an instance of [MainActivity]
     */

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

}