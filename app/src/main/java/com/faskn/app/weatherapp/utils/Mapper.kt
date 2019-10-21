package com.faskn.app.weatherapp.utils

/**
 * Created by Furkan on 2019-10-21
 */

interface Mapper<I, O> {
    fun map(type: I): O
}
