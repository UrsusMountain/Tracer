package com.ursus.base.service

import com.ursus.base.app.URApplication
import com.ursus.base.app.URContext
import com.ursus.base.log.Log

object URServiceManager {

    const val SERVICE_DEBUG = "debug"

    fun createActService(ctx: URContext, key: String): URActService? {
        var service: URActService? = when (key) {
            SERVICE_DEBUG -> DebugActService(ctx)
            else -> null
        }
        if (service == null) {
            service = URApplication.INSTANCE.createActService(ctx, key)
        }

        if(service != null) {
            Log.d(URActService.TAG, "URService ${service.javaClass.simpleName} created success")
        }

        return service
    }
}