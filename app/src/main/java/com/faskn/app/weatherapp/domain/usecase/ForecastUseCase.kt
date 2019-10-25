package com.faskn.app.weatherapp.domain.usecase

import androidx.lifecycle.LiveData
import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.repo.ForecastRepository
import com.faskn.app.weatherapp.utils.UseCaseLiveData
import com.faskn.app.weatherapp.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-21
 */

class ForecastUseCase @Inject internal constructor(private val repository: ForecastRepository) : UseCaseLiveData<Resource<ForecastEntity>, ForecastUseCase.ForecastParams, ForecastRepository>() {

    override fun getRepository(): ForecastRepository {
        return repository
    }

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
