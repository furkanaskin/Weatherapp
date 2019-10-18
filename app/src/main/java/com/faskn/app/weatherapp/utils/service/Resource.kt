package com.faskn.app.weatherapp.utils.service

/**
 * Created by Furkan on 2019-10-16
 */

import androidx.annotation.NonNull

// references :
// https://developer.android.com/jetpack/docs/guide#addendum

class Resource<T> constructor(val status: Status, val data: T?, val error: Throwable? = null) {

    companion object {

        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(status = Status.ERROR, data = null, error = throwable)
        }

        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null)
    }
}
