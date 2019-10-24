package com.faskn.app.weatherapp.ui.dashboard

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.databinding.FragmentDashboardBinding
import com.faskn.app.weatherapp.domain.usecase.forecast.CurrentWeatherUseCase
import com.faskn.app.weatherapp.domain.usecase.forecast.ForecastUseCase
import com.faskn.app.weatherapp.utils.extensions.isNetworkAvailable
import com.faskn.app.weatherapp.utils.extensions.toast
import com.google.android.material.chip.Chip

class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(DashboardFragmentViewModel::class.java) {

    private var chipItems: Array<String> = emptyArray()

    override fun getLayoutRes() = R.layout.fragment_dashboard

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()

        chipItems = resources.getStringArray(R.array.chip_titles)
        prepareChipGroup()

        viewModel.getForecast(ForecastUseCase.ForecastParams("Istanbul,TR", isNetworkAvailable(requireContext()), "metric"))
        viewModel.getForecastLiveData().observe(
            this,
            Observer {
                with(mBinding) {
                    viewState = it
                }
            }
        )

        viewModel.getCurrentWeather((CurrentWeatherUseCase.CurrentWeatherParams("Istanbul,TR", isNetworkAvailable(requireContext()), "metric")))
        viewModel.getCurrentWeatherLiveData().observe(
            this,
            Observer {
                with(mBinding) {
                    containerForecast.viewState = it
                }
            }
        )
    }

    private fun prepareChipGroup() {
        chipItems.forEachIndexed { index, item ->

            val chip = Chip(context)
            chip.text = item
            chip.tag = index
            chip.isClickable = true
            chip.isCheckable = true
            chip.isCloseIconVisible = false
            chip.isCheckedIconVisible = false
            chip.setChipBackgroundColorResource(R.color.white)
            chip.setTextColor(ContextCompat.getColor(this.context!!, R.color.mainTextColor))

            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    toast(chip.text, Toast.LENGTH_SHORT)
                    chip.setChipBackgroundColorResource(R.color.chipItemBackgroundColor)
                    chip.setTextColor(ContextCompat.getColor(this.context!!, R.color.chipTextColor))
                } else {
                    chip.setChipBackgroundColorResource(R.color.white)
                    chip.setTextColor(ContextCompat.getColor(this.context!!, R.color.mainTextColor))
                }
            }

            mBinding.chipGroupDays.addView(chip)
        }
    }
}
