package com.faskn.app.weatherapp.domain.usecase

import com.faskn.app.weatherapp.core.UseCase
import com.faskn.app.weatherapp.domain.mapper.ForecastMapper
import com.faskn.app.weatherapp.domain.model.ListItem
import com.faskn.app.weatherapp.repo.ForecastRepository
import com.faskn.app.weatherapp.utils.domain.Resource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-21
 */

class ForecastUseCase @Inject constructor(private val repository: ForecastRepository, private val mapper: ForecastMapper) : UseCase.ObservableUseCase<ForecastUseCase.ForecastParams, Resource<List<ListItem>>> {

    override fun execute(params: ForecastParams): Observable<Resource<List<ListItem>>> {
        return repository.getForecastByCityName(params.city)
            .map { resource ->
                Resource(
                    status = resource.status,
                    data = resource.data?.let { mapper.map(it) },
                    error = resource.error
                )
            }
    }

    class ForecastParams(
        val city: String = ""
    ) : UseCase.Params()
}
