package com.faskn.app.weatherapp.domain.usecase

import androidx.lifecycle.LiveData
import com.faskn.app.weatherapp.repo.ForecastRepository

/**
 * Created by Furkan on 2019-10-21
 */

abstract class UseCaseLiveData<M, P>(repository: ForecastRepository) {

    private val repository: ForecastRepository = repository

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

    fun getForecastRepository(): ForecastRepository {
        return repository
    }

    abstract class Params

    class None
}
