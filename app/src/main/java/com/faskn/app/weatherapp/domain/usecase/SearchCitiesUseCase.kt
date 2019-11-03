package com.faskn.app.weatherapp.domain.usecase

import androidx.lifecycle.LiveData
import com.faskn.app.weatherapp.db.entity.CitiesForSearchEntity
import com.faskn.app.weatherapp.repo.SearchCitiesRepository
import com.faskn.app.weatherapp.utils.AbsentLiveData
import com.faskn.app.weatherapp.utils.UseCaseLiveData
import com.faskn.app.weatherapp.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-31
 */

class SearchCitiesUseCase @Inject internal constructor(private val repository: SearchCitiesRepository) : UseCaseLiveData<Resource<List<CitiesForSearchEntity>>, SearchCitiesUseCase.SearchCitiesParams, SearchCitiesRepository>() {
    override fun getRepository(): SearchCitiesRepository = repository

    override fun buildUseCaseObservable(params: SearchCitiesParams?): LiveData<Resource<List<CitiesForSearchEntity>>> {
        return repository.loadCitiesByCityName(
            cityName = params?.city ?: ""
        )
    }

    data class SearchCitiesParams(
        val city: String = ""
    ) : Params() {
        fun <T> ifExists(f: (String) -> LiveData<T>): LiveData<T> {
            return if (city.isBlank()) {
                AbsentLiveData.create()
            } else {
                f(city)
            }
        }
    }
}
