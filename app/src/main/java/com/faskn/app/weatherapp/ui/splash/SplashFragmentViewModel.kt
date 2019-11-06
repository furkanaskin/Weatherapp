package com.faskn.app.weatherapp.ui.splash

import android.content.SharedPreferences
import com.faskn.app.weatherapp.core.BaseViewModel
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-16
 */

class SplashFragmentViewModel @Inject constructor(var sharedPreferences: SharedPreferences) : BaseViewModel() {
    var navigateDashboard = false
}
