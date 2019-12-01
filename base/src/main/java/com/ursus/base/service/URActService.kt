package com.ursus.base.service

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.ursus.base.app.URContext
import com.ursus.base.log.Log

abstract class URActService(ctx: URContext) : URService(ctx), DefaultLifecycleObserver {

    companion object {
        const val TAG = "URActService"
    }

    override fun onCreate(owner: LifecycleOwner) {
        Log.d(TAG, "URService(${owner}) ${this.javaClass.simpleName} onCreate ... ")
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d(TAG, "URService(${owner}) ${this.javaClass.simpleName} onStart ... ")
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d(TAG, "URService(${owner}) ${this.javaClass.simpleName} onResume ... ")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d(TAG, "URService(${owner}) ${this.javaClass.simpleName} onPause ... ")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d(TAG, "URService(${owner}) ${this.javaClass.simpleName} onStop ... ")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d(TAG, "URService(${owner}) ${this.javaClass.simpleName} onDestroy ... ")
    }
}