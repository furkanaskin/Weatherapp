package com.faskn.app.weatherapp.domain.usecase

import androidx.lifecycle.LiveData
import com.faskn.app.weatherapp.db.entity.CurrentWeatherEntity
import com.faskn.app.weatherapp.repo.CurrentWeatherRepository
import com.faskn.app.weatherapp.utils.UseCaseLiveData
import com.faskn.app.weatherapp.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-21
 */

class CurrentWeatherUseCase @Inject internal constructor(private val repository: CurrentWeatherRepository) : UseCaseLiveData<Resource<CurrentWeatherEntity>, CurrentWeatherUseCase.CurrentWeatherParams, CurrentWeatherRepository>() {

    override fun getRepository(): CurrentWeatherRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: CurrentWeatherParams?): LiveData<Resource<CurrentWeatherEntity>> {
        return repository.loadCurrentWeatherByGeoCords(
            params?.lat?.toDouble() ?: 0.0,
            params?.lon?.toDouble() ?: 0.0,
            params?.fetchRequired
                ?: false,
            units = params?.units ?: "metric"
        )
    }

    class CurrentWeatherParams(
        val lat: String = "",
        val lon: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
