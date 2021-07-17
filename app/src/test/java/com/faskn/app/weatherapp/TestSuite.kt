package com.faskn.app.weatherapp

import android.os.Build
import com.faskn.app.weatherapp.dao.CitiesForSearchDaoTest
import com.faskn.app.weatherapp.dao.CurrentWeatherDaoTest
import com.faskn.app.weatherapp.dao.ForecastDaoTest
import com.faskn.app.weatherapp.repo.CurrentWeatherRepositoryTest
import com.faskn.app.weatherapp.repo.ForecastRepositoryTest
import com.faskn.app.weatherapp.viewModel.DashboardViewModelTest
import com.faskn.app.weatherapp.viewModel.SearchViewModelTest
import com.faskn.app.weatherapp.viewModel.WeatherDetailViewModelTest
import com.faskn.app.weatherapp.viewState.CurrentWeatherViewStateTest
import com.faskn.app.weatherapp.viewState.ForecastViewStateTest
import com.faskn.app.weatherapp.viewState.SearchViewStateTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.robolectric.annotation.Config

/**
 * Created by Furkan on 2019-12-15
 */

@Config(sdk = [Build.VERSION_CODES.R])
@RunWith(Suite::class)
@Suite.SuiteClasses(
    CitiesForSearchDaoTest::class,
    CurrentWeatherDaoTest::class,
    CurrentWeatherViewStateTest::class,
    DashboardViewModelTest::class,
    ForecastDaoTest::class,
    ForecastViewStateTest::class,
    SearchViewStateTest::class,
    SearchViewModelTest::class,
    WeatherDetailViewModelTest::class,
    ForecastRepositoryTest::class,
    CurrentWeatherRepositoryTest::class
)
class TestSuite
