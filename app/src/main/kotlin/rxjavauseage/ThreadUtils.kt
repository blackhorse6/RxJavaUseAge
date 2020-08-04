package com.example.rxjavauseage

import android.util.Log

class ThreadUtils {
    fun current(tag: String?) {
        Log.d(tag, "当前线程=${Thread.currentThread()}")
    }
}