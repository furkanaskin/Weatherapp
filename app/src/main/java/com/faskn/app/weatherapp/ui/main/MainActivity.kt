package com.faskn.app.weatherapp.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseActivity
import com.faskn.app.weatherapp.databinding.ActivityMainBinding
import com.faskn.app.weatherapp.domain.usecase.ForecastUseCase
import com.faskn.app.weatherapp.utils.extensions.isNetworkAvailable

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java) {

    override fun initViewModel(mainActivityViewModel: MainActivityViewModel) {
        with(binding) {
            viewModel = mainActivityViewModel
        }
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getForecast(ForecastUseCase.ForecastParams("Istanbul,TR", isNetworkAvailable(this)))
        viewModel.getForecastLiveData().observe(
            this,
            Observer {
                with(binding) {
                    viewState = it
                }
            }
        )
    }
}
