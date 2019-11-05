package com.faskn.app.weatherapp.ui.dashboard

import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.databinding.FragmentDashboardBinding
import com.faskn.app.weatherapp.di.Injectable
import com.faskn.app.weatherapp.domain.model.ListItem
import com.faskn.app.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.faskn.app.weatherapp.domain.usecase.ForecastUseCase
import com.faskn.app.weatherapp.ui.dashboard.forecast.ForecastAdapter
import com.faskn.app.weatherapp.ui.main.MainActivity
import com.faskn.app.weatherapp.utils.extensions.isNetworkAvailable

class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(DashboardFragmentViewModel::class.java), Injectable {

    override fun getLayoutRes() = R.layout.fragment_dashboard

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        initForecastAdapter()

        val lat: String? = viewModel.sharedPreferences.getString("lat", "")
        val lon: String? = viewModel.sharedPreferences.getString("lon", "")

        if (lat?.isNotEmpty() == true && lon?.isNotEmpty() == true) {
            viewModel.currentWeatherParams.postValue(CurrentWeatherUseCase.CurrentWeatherParams(lat, lon, isNetworkAvailable(requireContext()), "metric"))
            viewModel.forecastParams.postValue(ForecastUseCase.ForecastParams(lat, lon, isNetworkAvailable(requireContext()), "metric"))
        }

        viewModel.getForecastViewState().observe(
            viewLifecycleOwner,
            Observer {
                with(mBinding) {
                    viewState = it
                    it.data?.list?.let { forecasts -> initForecast(forecasts) }
                    (activity as MainActivity).viewModel.toolbarTitle.set(it.data?.city?.getCityAndCountry())
                }
            }
        )

        viewModel.getCurrentWeatherViewState().observe(
            viewLifecycleOwner,
            Observer {
                with(mBinding) {
                    containerForecast.viewState = it
                }
            }
        )
    }

    private fun initForecastAdapter() {
        val adapter = ForecastAdapter { item, cardView, forecastIcon, dayOfWeek, temp, tempMaxMin ->
            val action = DashboardFragmentDirections.actionDashboardFragmentToWeatherDetailFragment(item)
            findNavController()
                .navigate(
                    action,
                    FragmentNavigator.Extras.Builder()
                        .addSharedElements(
                            mapOf(
                                cardView to "weatherItemCardView",
                                forecastIcon to "weatherItemForecastIcon",
                                dayOfWeek to "weatherItemDayOfWeek",
                                temp to "weatherItemTemp",
                                tempMaxMin to "weatherItemTempMaxMin"
                            )
                        )
                        .build()
                )
        }

        mBinding.recyclerForecast.adapter = adapter
        mBinding.recyclerForecast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initForecast(list: List<ListItem>) {
        (mBinding.recyclerForecast.adapter as ForecastAdapter).submitList(ForecastMapper().mapFrom(list))
    }
}
