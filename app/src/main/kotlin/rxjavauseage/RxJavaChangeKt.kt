package com.example.rxjavauseage

import android.util.Log
import com.example.rxjavauseage.UserDataUtil.mock
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.from
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.functions.Function3
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit


class RxJavaChangeKt {
    companion object {
        val TAG = "RxJavaChange"
    }

    fun flatMap() {
        Observable.just(mock())

            .subscribe {
                Log.d(TAG, "it=${it.password}")
            }
    }


}


