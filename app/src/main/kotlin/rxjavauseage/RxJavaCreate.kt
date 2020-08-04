package com.example.rxjavauseage

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers

class RxJavaCreate {
    companion object {
        val TAG = "RxJavaCreate"
    }

    val threadUtils = ThreadUtils()

    fun create() {
        Observable.create(ObservableOnSubscribe<String> {
            threadUtils.current(TAG)
            it.onNext("123")
        })
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                threadUtils.current(TAG)
            }
            .observeOn(Schedulers.computation())
            .map {
                threadUtils.current(TAG)
                "map==>${it}"
            }
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                threadUtils.current(TAG)
                "map2==>${it}"
            }
            .subscribe {
                threadUtils.current(TAG)
                Log.d(TAG, "it=${it}")
            }

    }

    fun repeat() {
        Observable
            .fromArray(1, 2, 3)
            .repeat(2)
            .subscribe {
                Log.d(TAG, "it=${it}")
            }
    }
}