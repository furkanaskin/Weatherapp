package com.faskn.app.weatherapp.domain

import com.faskn.app.weatherapp.core.Constants
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Furkan on 2019-10-21
 */

@Singleton
class PKRequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder().build()

        val request = chain.request().newBuilder().header(
            Constants.NetworkService.PK_API_KEY_HEADER_PARAM,
            Constants.NetworkService.PK_API_KEY_VALUE
        ).url(url).build()

        return chain.proceed(request)
    }
}
