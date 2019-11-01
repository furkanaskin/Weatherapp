package com.faskn.app.weatherapp.domain.usecase

import androidx.lifecycle.LiveData
import com.faskn.app.weatherapp.db.entity.CitiesForSearchEntity
import com.faskn.app.weatherapp.repo.SearchCitiesRepository
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
            cityName = params?.city ?: "",
            fetchRequired = params?.fetchRequired ?: true
        )
    }

    class SearchCitiesParams(
        val city: String = "",
        val fetchRequired: Boolean
    ) : Params()
}
