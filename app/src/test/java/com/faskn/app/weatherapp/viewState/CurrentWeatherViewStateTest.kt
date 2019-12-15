package com.faskn.app.weatherapp.viewState

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.faskn.app.weatherapp.ui.dashboard.CurrentWeatherViewState
import com.faskn.app.weatherapp.utils.domain.Status
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by Furkan on 2019-11-26
 */

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class CurrentWeatherViewStateTest {

    @Test
    fun `should return loading true when status is loading`() {
        // Given
        val givenViewState =
            CurrentWeatherViewState(status = Status.LOADING)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }

    @Test
    fun `should not return loading false when status is error`() {
        // Given
        val givenViewState =
            CurrentWeatherViewState(status = Status.SUCCESS)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should not return loading false when status is success`() {
        // Given
        val givenViewState = CurrentWeatherViewState(status = Status.ERROR)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should return correct error message when status is error`() {
        // Given
        val givenViewState =
            CurrentWeatherViewState(
                status = Status.ERROR,
                error = "500 Internal Server Error"
            )

        // When
        val actualResult = givenViewState.getErrorMessage()

        // Then
        Truth.assertThat(actualResult).isEqualTo("500 Internal Server Error")
    }

    @Test
    fun `should return true for error placeholder visibility  when status is error`() {
        // Given
        val givenViewState =
            CurrentWeatherViewState(
                status = Status.ERROR,
                error = "500 Internal Server Error"
            )

        // When
        val actualResult = givenViewState.shouldShowErrorMessage()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }
}
