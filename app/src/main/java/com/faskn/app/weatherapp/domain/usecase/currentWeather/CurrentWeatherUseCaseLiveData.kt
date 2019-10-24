package com.faskn.app.weatherapp.domain.usecase.forecast

import androidx.lifecycle.LiveData
import com.faskn.app.weatherapp.repo.CurrentWeatherRepository

/**
 * Created by Furkan on 2019-10-21
 */

abstract class CurrentWeatherUseCaseLiveData<M, P>(repository: CurrentWeatherRepository) {

    private val repository: CurrentWeatherRepository = repository

    abstract fun buildUseCaseObservable(params: P?): LiveData<M>

    /**
     * Executes the target call
     *
     * @param params represents params to be passed
     * @return [Disposable] result
     */
    fun execute(params: P?): LiveData<M> {
        return buildUseCaseObservable(params)
    }

    fun getCurrentWeatherRepository(): CurrentWeatherRepository {
        return repository
    }

    abstract class Params

    class None
}
