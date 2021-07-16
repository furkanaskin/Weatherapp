package com.faskn.app.weatherapp.repo

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.faskn.app.weatherapp.core.Constants
import com.faskn.app.weatherapp.db.entity.CurrentWeatherEntity
import com.faskn.app.weatherapp.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.faskn.app.weatherapp.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import com.faskn.app.weatherapp.util.createSampleCurrentWeatherResponse
import com.faskn.app.weatherapp.util.generateCurrentWeatherEntity
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
 * Created by Furkan on 2019-12-27
 */

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class CurrentWeatherRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource

    @MockK
    lateinit var currentWeatherLocalDataSource: CurrentWeatherLocalDataSource

    private lateinit var currentWeatherRepository: CurrentWeatherRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        currentWeatherRepository = CurrentWeatherRepository(
            currentWeatherRemoteDataSource,
            currentWeatherLocalDataSource
        )
    }

    @Test
    fun `given fetchRequired = false, when getCurrentWeather called, then make sure db called`() {
        // Given
        val fetchRequired = false
        val lat = 30.0
        val lon = 34.0
        val currentWeatherLiveData: MutableLiveData<CurrentWeatherEntity> = MutableLiveData()
        currentWeatherLiveData.postValue(generateCurrentWeatherEntity("Istanbul", 1))
        val mockedObserver: Observer<Resource<CurrentWeatherEntity>> = mockk(relaxUnitFun = true)

        // When
        every { currentWeatherLocalDataSource.getCurrentWeather() } returns currentWeatherLiveData
        every {
            currentWeatherRemoteDataSource.getCurrentWeatherByGeoCords(
                lat,
                lon,
                Constants.Coords.METRIC
            )
        } returns
            Single.just(createSampleCurrentWeatherResponse())

        currentWeatherRepository
            .loadCurrentWeatherByGeoCords(lat, lon, fetchRequired, Constants.Coords.METRIC)
            .observeForever(mockedObserver)

        /**
         * shouldFetch == false -> loadFromDb()
         */

        // Make sure network wasn't called
        verify {
            currentWeatherRemoteDataSource.getCurrentWeatherByGeoCords(
                lat,
                lon,
                Constants.Coords.METRIC
            ) wasNot called
        }
        // Make sure db called
        verify { currentWeatherLocalDataSource.getCurrentWeather() }

        // Then
        val currentWeatherEntitySlots = mutableListOf<Resource<CurrentWeatherEntity>>()
        verify { mockedObserver.onChanged(capture(currentWeatherEntitySlots)) }

        val currentWeatherEntity = currentWeatherEntitySlots[0]
        Truth.assertThat(currentWeatherEntity.status).isEqualTo(Status.SUCCESS)
        Truth.assertThat(currentWeatherEntity.data?.name).isEqualTo("Istanbul")
        Truth.assertThat(currentWeatherEntity.data?.id).isEqualTo(1) // generateCurrentWeatherEntity("Istanbul", 1) returns id as 1
    }

    @Test
    fun `given fetchRequired = true, when getCurrentWeather called, then make sure network called`() {
        // Given
        val fetchRequired = true
        val lat = 30.0
        val lon = 34.0
        val currentWeatherLiveData: MutableLiveData<CurrentWeatherEntity> = MutableLiveData()
        currentWeatherLiveData.postValue(CurrentWeatherEntity(createSampleCurrentWeatherResponse()))
        val mockedObserver: Observer<Resource<CurrentWeatherEntity>> = mockk(relaxUnitFun = true)

        // When
        every {
            currentWeatherRemoteDataSource.getCurrentWeatherByGeoCords(
                lat,
                lon,
                Constants.Coords.METRIC
            )
        } returns Single.just(createSampleCurrentWeatherResponse())
        every {
            currentWeatherLocalDataSource.insertCurrentWeather(
                createSampleCurrentWeatherResponse()
            )
        } just runs
        every { currentWeatherLocalDataSource.getCurrentWeather() } returns currentWeatherLiveData

        currentWeatherRepository
            .loadCurrentWeatherByGeoCords(lat, lon, fetchRequired, Constants.Coords.METRIC)
            .observeForever(mockedObserver)

        /**
         * shouldFetch == true -> createCall() -> saveCallResult() -> loadFromDb()
         */

        // Make sure network called
        verify {
            currentWeatherRemoteDataSource.getCurrentWeatherByGeoCords(
                lat,
                lon,
                Constants.Coords.METRIC
            )
        }
        // Make sure db called
        verify { currentWeatherLocalDataSource.getCurrentWeather() }

        // Then
        val currentWeatherEntitySlots = mutableListOf<Resource<CurrentWeatherEntity>>()
        verify { mockedObserver.onChanged(capture(currentWeatherEntitySlots)) }

        val currentWeatherEntity = currentWeatherEntitySlots[0]

        Truth.assertThat(currentWeatherEntity.data?.name).isEqualTo("Istanbul")
        // CurrentWeatherEntity(currentWeatherResponse : CurrentWeatherResponse) constructor defines id as 0
        Truth.assertThat(currentWeatherEntity.data?.id).isEqualTo(0)
    }
}
