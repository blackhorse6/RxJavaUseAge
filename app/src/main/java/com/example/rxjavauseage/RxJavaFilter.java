package com.example.rxjavauseage;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class RxJavaFilter {
    private static final String TAG = "RxJavaFilter";
    static Observable observable;
    static Observable observable2;

    static {
        observable = Observable.create(emitter -> {
            emitter.onNext("A");
            Thread.sleep(1_500);
            emitter.onNext("B");
            Thread.sleep(500);
            emitter.onNext("C");
            Thread.sleep(250);
            emitter.onNext("D");
            Thread.sleep(2_000);
            emitter.onNext("E");
            emitter.onComplete();

        });
        observable2 = Observable.create(emitter -> {
            emitter.onNext("A");
            Thread.sleep(500);
            emitter.onNext("B");
            Thread.sleep(200);
            emitter.onNext("C");
            Thread.sleep(800);
            emitter.onNext("D");
            Thread.sleep(600);
            emitter.onNext("E");
            emitter.onComplete();

        });
    }

    /**
     * 发射之后一定时间内无发射，则选择
     * A D E
     */
    public static void debounce() {
        observable.debounce(1, TimeUnit.SECONDS)
                .subscribe(o -> Log.d(TAG, "method=【debounce】==>" + o));


    }

    /**
     * 周期采样，取最后一个
     * C D
     */
    public static void sample() {
        observable2.sample(1, TimeUnit.SECONDS)
                .subscribe(o -> Log.d(TAG, "method=【sample】==>" + o));

    }

    /**
     * 一段周期采样，取第一个
     * A D
     */
    public static void throttleFirst() {
        observable2.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> Log.d(TAG, "method=【throttleFirst】==>" + o));
    }

    /**
     * ==sample
     * C D
     */
    public static void throttleLast() {
        observable2.throttleLast(1, TimeUnit.SECONDS)
                .subscribe(o -> Log.d(TAG, "method=【throttleLast】==>" + o));

    }

    /**
     * ==debounce
     * A D E
     */
    public static void throttleWithTimeOut() {
        observable.throttleWithTimeout(1, TimeUnit.SECONDS)
                .subscribe(o -> Log.d(TAG, "method=【throttleWithTimeOut】==>" + o));

    }
}
