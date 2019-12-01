package com.ursus.media

import android.content.Context
import com.ursus.base.app.IApplication
import com.ursus.base.app.URContext
import com.ursus.base.log.Log
import com.ursus.base.service.URActService
import com.ursus.base.service.URService
import com.ursus.media.service.MediaService

class MediaApplication : IApplication {


    companion object {
        const val TAG = "MediaApplication"

        const val SERVICE_MEDIA = "media"
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

    override fun createActService(ctx: URContext, key: String): URActService? = when (key) {
        SERVICE_MEDIA -> MediaService(ctx)
        else -> null
    }

    override fun createAppService(ctx: URContext, key: String): URService? = null
}