package com.ursus.tracer

import com.ursus.base.app.URApplication
import com.ursus.base.app.URContext
import com.ursus.base.service.URActService
import com.ursus.tracer.service.TaskActService

class TracerApplication : URApplication() {

    companion object {
        const val SERVICE_TASK = "task"
    }

    override fun createActService(ctx: URContext, key: String): URActService? = when (key) {
        SERVICE_TASK -> TaskActService(ctx)
        else -> super.createActService(ctx, key)
    }
}