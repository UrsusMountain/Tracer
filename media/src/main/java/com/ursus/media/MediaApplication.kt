package com.ursus.media

import android.content.Context
import com.ursus.base.app.IApplication
import com.ursus.base.log.Log

class MediaApplication : IApplication {

    companion object {
        const val TAG = "MediaApplication"
    }

    init {
        Log.d(TAG, "MediaApplication init ... ")
    }

    override fun attachBaseContext(context: Context) {
        Log.d(TAG, "MediaApplication attachBaseContext() ... ")
    }

    override fun onCreate(context: Context) {
        Log.d(TAG, "MediaApplication onCreate() ... ")
    }

}