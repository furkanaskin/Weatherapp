package com.faskn.app.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.domain.usecase.ForecastUseCase
import com.faskn.app.weatherapp.utils.domain.Resource
import javax.inject.Inject

class MainActivityViewModel @Inject internal constructor(private val useCase: ForecastUseCase) : BaseViewModel() {

    private var forecastLiveData: LiveData<Resource<ForecastEntity>> = MutableLiveData()

    fun getForecast(params: ForecastUseCase.ForecastParams): LiveData<Resource<ForecastEntity>> {
        forecastLiveData =
            Transformations.switchMap(
                useCase.execute(params)
            ) {
                val forecastLiveData = MutableLiveData<Resource<ForecastEntity>>()
                forecastLiveData.value = it
                return@switchMap forecastLiveData
            }

        return forecastLiveData
    }

    fun getForecastLiveData() = forecastLiveData
}
