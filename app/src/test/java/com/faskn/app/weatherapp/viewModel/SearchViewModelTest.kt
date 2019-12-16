package com.faskn.app.weatherapp.viewModel

import android.content.SharedPreferences
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.faskn.app.weatherapp.domain.usecase.SearchCitiesUseCase
import com.faskn.app.weatherapp.ui.search.SearchViewModel
import com.faskn.app.weatherapp.ui.search.SearchViewState
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
 * Created by Furkan on 2019-12-15
 */

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class SearchViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var searchCitiesUseCase: SearchCitiesUseCase

    @MockK
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        searchViewModel = SearchViewModel(searchCitiesUseCase, sharedPreferences)
    }

    @Test
    fun `given loading state, when setSearchParams called, then update live data for loading status`() {
        // Given
        val viewStateObserver: Observer<SearchViewState> = mockk(relaxUnitFun = true)
        searchViewModel.getSearchViewState().observeForever(viewStateObserver)

        val viewStateLiveData: MutableLiveData<SearchViewState> = MutableLiveData()
        viewStateLiveData.postValue(SearchViewState(Status.LOADING, null, null))

        // When
        every { searchCitiesUseCase.execute(any()) } returns viewStateLiveData
        searchViewModel.setSearchParams(SearchCitiesUseCase.SearchCitiesParams("city"))

        // Then
        val forecastViewStateSlots = mutableListOf<SearchViewState>()
        verify { viewStateObserver.onChanged(capture(forecastViewStateSlots)) }

        val loadingState = forecastViewStateSlots[0]
        Truth.assertThat(loadingState.status).isEqualTo(Status.LOADING)
    }

    @Test
    fun `given error state, when setSearchParams called, then update live data for error status`() {
        // Given
        val viewStateObserver: Observer<SearchViewState> = mockk(relaxUnitFun = true)
        searchViewModel.getSearchViewState().observeForever(viewStateObserver)

        val viewStateLiveData: MutableLiveData<SearchViewState> = MutableLiveData()
        viewStateLiveData.postValue(SearchViewState(Status.ERROR, null, null))

        // When
        every { searchCitiesUseCase.execute(any()) } returns viewStateLiveData
        searchViewModel.setSearchParams(SearchCitiesUseCase.SearchCitiesParams("city"))

        // Then
        val forecastViewStateSlots = mutableListOf<SearchViewState>()
        verify { viewStateObserver.onChanged(capture(forecastViewStateSlots)) }

        val errorState = forecastViewStateSlots[0]
        Truth.assertThat(errorState.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `given success state, when setSearchParams called, then update live data for success status`() {
        // Given
        val viewStateObserver: Observer<SearchViewState> = mockk(relaxUnitFun = true)
        searchViewModel.getSearchViewState().observeForever(viewStateObserver)

        val viewStateLiveData: MutableLiveData<SearchViewState> = MutableLiveData()
        viewStateLiveData.postValue(SearchViewState(Status.SUCCESS, null, null))

        // When
        every { searchCitiesUseCase.execute(any()) } returns viewStateLiveData
        searchViewModel.setSearchParams(SearchCitiesUseCase.SearchCitiesParams("city"))

        // Then
        val forecastViewStateSlots = mutableListOf<SearchViewState>()
        verify { viewStateObserver.onChanged(capture(forecastViewStateSlots)) }

        val successState = forecastViewStateSlots[0]
        Truth.assertThat(successState.status).isEqualTo(Status.SUCCESS)
    }
}
