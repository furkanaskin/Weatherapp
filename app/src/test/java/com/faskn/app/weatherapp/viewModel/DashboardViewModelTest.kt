package com.faskn.app.weatherapp.viewModel

import android.content.SharedPreferences
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.faskn.app.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.faskn.app.weatherapp.domain.usecase.ForecastUseCase
import com.faskn.app.weatherapp.ui.dashboard.CurrentWeatherViewState
import com.faskn.app.weatherapp.ui.dashboard.DashboardFragmentViewModel
import com.faskn.app.weatherapp.ui.dashboard.ForecastViewState
import com.faskn.app.weatherapp.utils.domain.Status
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by Furkan on 2019-12-13
 */

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class DashboardViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var currentWeatherUseCase: CurrentWeatherUseCase

    @MockK
    lateinit var forecastUseCase: ForecastUseCase

    @MockK
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var dashboardFragmentViewModel: DashboardFragmentViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dashboardFragmentViewModel = DashboardFragmentViewModel(
            forecastUseCase,
            currentWeatherUseCase,
            sharedPreferences
        )
    }

    @Test
    fun `given loading state, when setForecastParams called, then update live data for loading status`() {
        // Given
        val viewStateObserver: Observer<ForecastViewState> = mockk(relaxUnitFun = true)
        dashboardFragmentViewModel.getForecastViewState().observeForever(viewStateObserver)

        val viewStateLiveData: MutableLiveData<ForecastViewState> = MutableLiveData()
        viewStateLiveData.postValue(ForecastViewState(Status.LOADING, null, null))

        // When
        every { forecastUseCase.execute(any()) } returns viewStateLiveData
        dashboardFragmentViewModel.setForecastParams(
            ForecastUseCase.ForecastParams("30", "32", true, "metric")
        )

        // Then
        val forecastViewStateSlots = mutableListOf<ForecastViewState>()
        verify { viewStateObserver.onChanged(capture(forecastViewStateSlots)) }

        val loadingState = forecastViewStateSlots[0]
        Truth.assertThat(loadingState.status).isEqualTo(Status.LOADING)
    }

    @Test
    fun `given error state, when setForecastParams called, then update live data for error status`() {
        // Given
        val viewStateObserver: Observer<ForecastViewState> = mockk(relaxUnitFun = true)
        dashboardFragmentViewModel.getForecastViewState().observeForever(viewStateObserver)

        val viewStateLiveData: MutableLiveData<ForecastViewState> = MutableLiveData()
        viewStateLiveData.postValue(ForecastViewState(Status.ERROR, "Unhandled Exception", null))

        // When
        every { forecastUseCase.execute(any()) } returns viewStateLiveData
        dashboardFragmentViewModel.setForecastParams(
            ForecastUseCase.ForecastParams("30", "32", true, "metric")
        )

        // Then
        val forecastViewStateSlots = mutableListOf<ForecastViewState>()
        verify { viewStateObserver.onChanged(capture(forecastViewStateSlots)) }

        val errorState = forecastViewStateSlots[0]
        Truth.assertThat(errorState.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `given success state, when setForecastParams called, then update live data for success status`() {
        // Given
        val viewStateObserver: Observer<ForecastViewState> = mockk(relaxUnitFun = true)
        dashboardFragmentViewModel.getForecastViewState().observeForever(viewStateObserver)

        val viewStateLiveData: MutableLiveData<ForecastViewState> = MutableLiveData()
        viewStateLiveData.postValue(ForecastViewState(Status.SUCCESS, null, null))

        // When
        every { forecastUseCase.execute(any()) } returns viewStateLiveData
        dashboardFragmentViewModel.setForecastParams(
            ForecastUseCase.ForecastParams("30", "32", true, "metric")
        )

        // Then
        val forecastViewStateSlots = mutableListOf<ForecastViewState>()
        verify { viewStateObserver.onChanged(capture(forecastViewStateSlots)) }

        val successState = forecastViewStateSlots[0]
        Truth.assertThat(successState.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `given loading state, when setCurrentWeatherParams called, then update live data for loading status`() {
        // Given
        val viewStateObserver: Observer<CurrentWeatherViewState> = mockk(relaxUnitFun = true)
        dashboardFragmentViewModel.getCurrentWeatherViewState().observeForever(viewStateObserver)

        val viewStateLiveData: MutableLiveData<CurrentWeatherViewState> = MutableLiveData()
        viewStateLiveData.postValue(CurrentWeatherViewState(Status.LOADING, null, null))

        // When
        every { currentWeatherUseCase.execute(any()) } returns viewStateLiveData
        dashboardFragmentViewModel.setCurrentWeatherParams(
            CurrentWeatherUseCase.CurrentWeatherParams("30", "32", true, "metric")
        )

        // Then
        val currentWeatherViewStateSlots = mutableListOf<CurrentWeatherViewState>()
        verify { viewStateObserver.onChanged(capture(currentWeatherViewStateSlots)) }

        val loadingState = currentWeatherViewStateSlots[0]
        Truth.assertThat(loadingState.status).isEqualTo(Status.LOADING)
    }

    @Test
    fun `given error state, when setCurrentWeatherParams called, then update live data for error status`() {
        // Given
        val viewStateObserver: Observer<CurrentWeatherViewState> = mockk(relaxUnitFun = true)
        dashboardFragmentViewModel.getCurrentWeatherViewState().observeForever(viewStateObserver)

        val viewStateLiveData: MutableLiveData<CurrentWeatherViewState> = MutableLiveData()
        viewStateLiveData.postValue(CurrentWeatherViewState(Status.ERROR, null, null))

        // When
        every { currentWeatherUseCase.execute(any()) } returns viewStateLiveData
        dashboardFragmentViewModel.setCurrentWeatherParams(
            CurrentWeatherUseCase.CurrentWeatherParams("30", "32", true, "metric")
        )

        // Then
        val currentWeatherViewStateSlots = mutableListOf<CurrentWeatherViewState>()
        verify { viewStateObserver.onChanged(capture(currentWeatherViewStateSlots)) }

        val errorState = currentWeatherViewStateSlots[0]
        Truth.assertThat(errorState.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `given success state, when setCurrentWeatherParams called, then update live data for success status`() {
        // Given
        val viewStateObserver: Observer<CurrentWeatherViewState> = mockk(relaxUnitFun = true)
        dashboardFragmentViewModel.getCurrentWeatherViewState().observeForever(viewStateObserver)

        val viewStateLiveData: MutableLiveData<CurrentWeatherViewState> = MutableLiveData()
        viewStateLiveData.postValue(CurrentWeatherViewState(Status.SUCCESS, null, null))

        // When
        every { currentWeatherUseCase.execute(any()) } returns viewStateLiveData
        dashboardFragmentViewModel.setCurrentWeatherParams(
            CurrentWeatherUseCase.CurrentWeatherParams("30", "32", true, "metric")
        )

        // Then
        val currentWeatherViewStateSlots = mutableListOf<CurrentWeatherViewState>()
        verify { viewStateObserver.onChanged(capture(currentWeatherViewStateSlots)) }

        val successState = currentWeatherViewStateSlots[0]
        Truth.assertThat(successState.status).isEqualTo(Status.SUCCESS)
    }
}
