package com.faskn.app.weatherapp.domain.usecase.forecast

import androidx.lifecycle.LiveData
import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.repo.ForecastRepository
import com.faskn.app.weatherapp.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-21
 */

class ForecastUseCase @Inject internal constructor(private val repository: ForecastRepository) : ForecastUseCaseLiveData<Resource<ForecastEntity>, ForecastUseCase.ForecastParams>(repository) {

    override fun buildUseCaseObservable(params: ForecastParams?): LiveData<Resource<ForecastEntity>> {
        return repository.loadForecastByCityName(
            params?.city ?: "",
            params?.fetchRequired
                ?: false,
            units = params?.units ?: "metric"
        )
    }

    class ForecastParams(
        val city: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
