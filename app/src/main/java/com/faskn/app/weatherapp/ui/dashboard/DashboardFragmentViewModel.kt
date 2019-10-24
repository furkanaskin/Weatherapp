package com.faskn.app.weatherapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.domain.usecase.ForecastUseCase
import com.faskn.app.weatherapp.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-24
 */

class DashboardFragmentViewModel @Inject internal constructor(private val useCase: ForecastUseCase) : BaseViewModel() {

    private var forecastLiveData: LiveData<DashboardFragmentViewState> = MutableLiveData()

    fun getForecast(params: ForecastUseCase.ForecastParams): LiveData<DashboardFragmentViewState> {
        forecastLiveData =
            Transformations.switchMap(
                useCase.execute(params)
            ) {
                val forecastLiveData = MutableLiveData<DashboardFragmentViewState>()
                forecastLiveData.value = onForecastResultReady(it)
                return@switchMap forecastLiveData
            }

        return forecastLiveData
    }

    fun getForecastLiveData() = forecastLiveData

    private fun onForecastResultReady(resource: Resource<ForecastEntity>): DashboardFragmentViewState {
        return DashboardFragmentViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }
}
