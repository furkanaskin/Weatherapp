package com.faskn.app.weatherapp.core

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Furkan on 2019-10-21
 */

interface UseCase {

    interface RequestUseCase<P : Params, T : Any> {
        fun execute(params: P): T
    }

    interface ObservableUseCase<P : Params, T : Any> : UseCase {
        fun execute(params: P): Observable<T>
    }

    interface FlowableUseCase<P : Params, T : Any> : UseCase {
        fun execute(params: P): Flowable<T>
    }

    interface CompletableUseCase<P : Params> : UseCase {
        fun execute(params: P): Completable
    }

    interface SingleUseCase<P : Params, T : Any> : UseCase {
        fun execute(params: P): Single<T>
    }

    abstract class Params

    class None : Params()
}
