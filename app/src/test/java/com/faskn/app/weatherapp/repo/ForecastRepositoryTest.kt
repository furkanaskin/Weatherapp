package com.faskn.app.weatherapp.repo

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.faskn.app.weatherapp.core.Constants
import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.faskn.app.weatherapp.domain.datasource.forecast.ForecastRemoteDataSource
import com.faskn.app.weatherapp.util.createSampleForecastResponse
import com.faskn.app.weatherapp.utils.domain.Resource
import com.faskn.app.weatherapp.utils.domain.Status
import com.google.common.truth.Truth
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by Furkan on 2019-12-17
 */

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class ForecastRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var forecastRemoteDataSource: ForecastRemoteDataSource

    @MockK
    lateinit var forecastLocalDataSource: ForecastLocalDataSource

    private lateinit var forecastRepository: ForecastRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        forecastRepository = ForecastRepository(forecastRemoteDataSource, forecastLocalDataSource)
    }

    @Test
    fun `given fetchRequired = false, when loadForecastByCoord called, then make sure db called`() {
        // Given
        val fetchRequired = false
        val lat = 30.0
        val lon = 34.0
        val forecastLiveData: MutableLiveData<ForecastEntity> = MutableLiveData()
        forecastLiveData.postValue(createSampleForecastResponse(1, "Istanbul"))
        val mockedObserver: Observer<Resource<ForecastEntity>> = mockk(relaxUnitFun = true)

        // When
        every { forecastLocalDataSource.getForecast() } returns forecastLiveData
        every { forecastRemoteDataSource.getForecastByGeoCords(lat, lon, Constants.Coords.METRIC) } returns
            Single.just(createSampleForecastResponse())

        forecastRepository
            .loadForecastByCoord(lat, lon, fetchRequired, Constants.Coords.METRIC)
            .observeForever(mockedObserver)

        /**
         * shouldFetch == false -> loadFromDb()
         */

        // Make sure network wasn't called
        verify { forecastRemoteDataSource.getForecastByGeoCords(lat, lon, Constants.Coords.METRIC) wasNot called }
        // Make sure db called
        verify { forecastLocalDataSource.getForecast() }

        // Then
        val forecastEntitySlots = mutableListOf<Resource<ForecastEntity>>()
        verify { mockedObserver.onChanged(capture(forecastEntitySlots)) }

        val forecastEntity = forecastEntitySlots[0]
        Truth.assertThat(forecastEntity.status).isEqualTo(Status.SUCCESS)
        Truth.assertThat(forecastEntity.data?.city?.cityName).isEqualTo("Istanbul")
        Truth.assertThat(forecastEntity.data?.id).isEqualTo(1) // createSampleForecastResponse(1, "Istanbul") returns id as 1
    }

    @Test
    fun `given fetchRequired = true, when loadForecastByCoord called, then make sure network called`() {
        // Given
        val fetchRequired = true
        val lat = 30.0
        val lon = 34.0
        val forecastLiveData: MutableLiveData<ForecastEntity> = MutableLiveData()
        forecastLiveData.postValue(ForecastEntity(createSampleForecastResponse()))
        val mockedObserver: Observer<Resource<ForecastEntity>> = mockk(relaxUnitFun = true)

        // When
        every { forecastRemoteDataSource.getForecastByGeoCords(lat, lon, Constants.Coords.METRIC) } returns Single.just(
            createSampleForecastResponse()
        )
        every { forecastLocalDataSource.insertForecast(createSampleForecastResponse()) } just runs
        every { forecastLocalDataSource.getForecast() } returns forecastLiveData

        forecastRepository
            .loadForecastByCoord(lat, lon, fetchRequired, Constants.Coords.METRIC)
            .observeForever(mockedObserver)

        /**
         * shouldFetch == true -> createCall() -> saveCallResult() -> loadFromDb()
         */

        // Make sure network called
        verify { forecastRemoteDataSource.getForecastByGeoCords(lat, lon, Constants.Coords.METRIC) }
        // Make sure db called
        verify { forecastLocalDataSource.getForecast() }

        // Then
        val forecastEntitySlots = mutableListOf<Resource<ForecastEntity>>()
        verify { mockedObserver.onChanged(capture(forecastEntitySlots)) }

        val forecastEntity = forecastEntitySlots[0]

        Truth.assertThat(forecastEntity.data?.city?.cityName).isEqualTo("Istanbul")
        // ForecastEntity(forecastResponse : ForecastResponse) constructor defines id as 0
        Truth.assertThat(forecastEntity.data?.id).isEqualTo(0)
    }
}
