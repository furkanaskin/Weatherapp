package com.faskn.app.weatherapp.ui.weather_detail

import android.transition.TransitionInflater
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.databinding.FragmentWeatherDetailBinding
import com.faskn.app.weatherapp.di.Injectable

class WeatherDetailFragment : BaseFragment<WeatherDetailViewModel, FragmentWeatherDetailBinding>(WeatherDetailViewModel::class.java), Injectable {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_weather_detail
    }

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        val transition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }
}
