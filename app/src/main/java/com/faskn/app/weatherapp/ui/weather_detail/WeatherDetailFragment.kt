package com.faskn.app.weatherapp.ui.weather_detail

import android.transition.TransitionInflater
import androidx.navigation.fragment.navArgs
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.databinding.FragmentWeatherDetailBinding
import com.faskn.app.weatherapp.di.Injectable

class WeatherDetailFragment : BaseFragment<WeatherDetailViewModel, FragmentWeatherDetailBinding>(WeatherDetailViewModel::class.java), Injectable {

    private val weatherDetailFragmentArgs: WeatherDetailFragmentArgs by navArgs()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_weather_detail
    }

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        viewModel.backgroundColor.set(weatherDetailFragmentArgs.backgroundColor)
        val transition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }
}
