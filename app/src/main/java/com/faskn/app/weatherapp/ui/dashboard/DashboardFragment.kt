package com.faskn.app.weatherapp.ui.dashboard

import androidx.lifecycle.Observer
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.databinding.FragmentDashboardBinding
import com.faskn.app.weatherapp.domain.usecase.ForecastUseCase
import com.faskn.app.weatherapp.utils.extensions.isNetworkAvailable

class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(DashboardFragmentViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_dashboard

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()

        viewModel.getForecast(ForecastUseCase.ForecastParams("Istanbul,TR", isNetworkAvailable(requireContext())))
        viewModel.getForecastLiveData().observe(
            this,
            Observer {
                with(mBinding) {
                    viewState = it
                }
            }
        )
    }
}
