<a href="https://github.com/furkanaskin/Weatherapp/blob/dev/CONTRIBUTING.md"><img src="https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat" alt="contributions welcome" /></a>
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9499cbb9d3864f9fb289be4bf34b57ce)](https://www.codacy.com/manual/furkanaskin/Weatherapp?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=furkanaskin/Weatherapp&amp;utm_campaign=Badge_Grade)
<a href="https://android-arsenal.com/api?level=21"><img src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat" alt="API" /></a>
<a href="https://ktlint.github.io/"><img src="https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg" alt="ktlint" /></a>
[![Build Status](https://github.com/furkanaskin/Weatherapp/workflows/Weatherapp/badge.svg)](https://github.com/furkanaskin/Weatherapp/actions)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23393-06a9f9)](https://androidweekly.net/issues/issue-393)
[![Student Gallery](https://img.shields.io/badge/Part%20of-Student%20Gallery-9cf?logo=github)](https://education.github.com/pack/gallery#weatherapp)
[![DevLibrary](https://img.shields.io/badge/Part%20of-DevLibrary-9cf?color=4285F4&logoColor=4285F4&logo=google)](https://devlibrary.withgoogle.com/products/android/repos/furkanaskin-Weatherapp)
# Weatherapp
![appicon](https://user-images.githubusercontent.com/22769589/68296145-f7305d80-00a4-11ea-9cbe-24b18222bfa9.png)

Weatherapp is a simple weather forecast app, which uses some APIs to fetch 5 day / 3 hour forecast data from the [OpenWeatherMap](https://openweathermap.org/forecast5) and to fetch places,cities,counties,coords etc. from [Algolia Places](https://community.algolia.com/places/). The main goal of this app is to be a sample of how to build an high quality Android application that uses the Architecture components, Hilt etc. in Kotlin.

<h2 id="Outputs">Outputs</h2>
<p><img height= "200" src="https://user-images.githubusercontent.com/22769589/68296813-82f6b980-00a6-11ea-80bc-7a0e36e6336f.gif" alt="Gif1" />
<img height= "200" src="https://user-images.githubusercontent.com/22769589/73372162-ce94ba00-42c7-11ea-9100-642953ec9c7e.gif" alt="Gif2" />
<img height= "200" src="https://user-images.githubusercontent.com/22769589/68296812-82f6b980-00a6-11ea-946b-ede239a4446a.gif" alt="Gif3" />
<img height= "200" src="https://user-images.githubusercontent.com/22769589/68296815-838f5000-00a6-11ea-86ed-8587458d2bb2.gif" alt="Gif4" /></p>

## Libraries and tools 🛠

<li><a href="https://developer.android.com/topic/libraries/architecture/navigation/">Navigation</a></li>
<li><a href="https://developer.android.com/training/data-storage/shared-preferences">Shared Preferences</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/livedata">LiveData</a></li>
<li><a href="https://developer.android.com/reference/androidx/lifecycle/Transformations">Transformations</a></li>
<li><a href="https://developer.android.com/topic/libraries/data-binding">Data Binding</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/room">RoomDB</a></li>
<li><a href="https://developer.android.com/training/dependency-injection/hilt-android">Hilt</a></li>
<li><a href="https://github.com/ReactiveX/RxJava">RxJava</a></li>
<li><a href="https://github.com/ReactiveX/RxAndroid">RxAndroid</a></li>
<li><a href="https://github.com/ReactiveX/RxKotlin">RxKotlin</a></li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a></li>
<li><a href="https://github.com/square/okhttp">OkHttp</a></li>
<li><a href="https://github.com/square/moshi">Moshi</a></li>
<li><a href="https://github.com/facebook/stetho">Stetho</a></li>
<li><a href="https://github.com/square/picasso">Picasso</a></li>
<li><a href="https://material.io/develop/android/docs/getting-started/">Material Design</a></li>
<li><a href="https://github.com/lopspower/RxAnimation">RxAnimation</a></li>
<li><a href="https://github.com/JakeWharton/ThreeTenABP">ThreeTenABP</a></li>
<li><a href="https://github.com/pinterest/ktlint">Ktlint</a></li>
<li><a href="https://github.com/algolia/algoliasearch-client-android">Algolia Search API Client for Android</a></li>
<li><a href="https://github.com/loopeer/shadow">Shadow</a></li>

## Testing 🧪
<li><a href="https://github.com/mockk/mockk">Mockk</a></li>
<li><a href="https://github.com/google/truth">Truth</a></li>

## Architecture
The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

## Dependency Graph 🔪
The following diagram shows the dependency graph of the app.

![Screen Shot 2019-11-20 at 12 03 24](https://user-images.githubusercontent.com/22769589/69224544-ce709380-0b8d-11ea-9bb5-51e9ea8828c9.png)

Generated by [Daggraph](https://github.com/dvdciri/daggraph)

## Design
Inspired by [Ramonyv](https://www.uplabs.com/posts/weather-app-freebie) and weather icons taken from [isneezy/open-weather-icons](https://github.com/isneezy/open-weather-icons)

<h2 id="license">License</h2>
<pre><code>

Copyright (c) 2021 Furkan Aşkın

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
</code></pre>
