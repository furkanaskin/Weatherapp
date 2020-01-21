package com.faskn.app.weatherapp.ui.weather_detail

import android.transition.TransitionInflater
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.databinding.FragmentWeatherDetailBinding
import com.faskn.app.weatherapp.di.Injectable
import com.faskn.app.weatherapp.domain.model.ListItem
import com.faskn.app.weatherapp.ui.weather_detail.weatherHourOfDay.WeatherHourOfDayAdapter
import com.faskn.app.weatherapp.utils.extensions.observeWith
import io.reactivex.disposables.CompositeDisposable

class WeatherDetailFragment : BaseFragment<WeatherDetailViewModel, FragmentWeatherDetailBinding>(WeatherDetailViewModel::class.java), Injectable {

    private val weatherDetailFragmentArgs: WeatherDetailFragmentArgs by navArgs()
    var disposable = CompositeDisposable()

    override fun getLayoutRes(): Int = R.layout.fragment_weather_detail

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        viewModel.weatherItem.set(weatherDetailFragmentArgs.weatherItem)
        viewModel.selectedDayDate = weatherDetailFragmentArgs.weatherItem.dtTxt?.substringBefore(" ")

        viewModel.getForecast().observeWith(viewLifecycleOwner) {
            viewModel.selectedDayForecastLiveData
                .postValue(
                    it.list?.filter { item ->
                        item.dtTxt?.substringBefore(" ") == viewModel.selectedDayDate
                    }
                )
        }

        viewModel.selectedDayForecastLiveData.observeWith(
            viewLifecycleOwner
        ) {
            initWeatherHourOfDayAdapter(it)
        }

        mBinding.fabClose.setOnClickListener {
            findNavController().popBackStack()
        }

        val inflateTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = inflateTransition
    }

    private fun initWeatherHourOfDayAdapter(list: List<ListItem>) {
        val adapter = WeatherHourOfDayAdapter { item ->
            // TODO - onClick
        }

        mBinding.recyclerViewHourOfDay.adapter = adapter
        (mBinding.recyclerViewHourOfDay.adapter as WeatherHourOfDayAdapter).submitList(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
