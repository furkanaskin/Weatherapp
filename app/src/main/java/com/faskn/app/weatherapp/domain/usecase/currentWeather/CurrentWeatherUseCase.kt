package com.faskn.app.weatherapp.domain.usecase.forecast

import androidx.lifecycle.LiveData
import com.faskn.app.weatherapp.db.entity.CurrentWeatherEntity
import com.faskn.app.weatherapp.repo.CurrentWeatherRepository
import com.faskn.app.weatherapp.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-21
 */

class CurrentWeatherUseCase @Inject internal constructor(private val repository: CurrentWeatherRepository) : CurrentWeatherUseCaseLiveData<Resource<CurrentWeatherEntity>, CurrentWeatherUseCase.CurrentWeatherParams>(repository) {

    override fun buildUseCaseObservable(params: CurrentWeatherParams?): LiveData<Resource<CurrentWeatherEntity>> {
        return repository.loadCurrentWeatherByCityName(
            params?.city ?: "",
            params?.fetchRequired
                ?: false,
            units = params?.units ?: "metric"
        )
    }

    class CurrentWeatherParams(
        val city: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
