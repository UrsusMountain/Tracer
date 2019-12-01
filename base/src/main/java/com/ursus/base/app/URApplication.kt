package com.ursus.base.app

import android.app.Application
import android.content.Context
import com.ursus.base.log.Log
import com.ursus.base.service.*
import com.ursus.base.utils.ManifestUtils

fun URApplication.getInstance(): URApplication = this

abstract class URApplication : Application(), URContext {

    private val applicationAgent by lazy { ApplicationAgent() }
    private val services by lazy { mutableMapOf<String, URService>() }

    companion object {
        lateinit var INSTANCE: URApplication

        const val SERVICE_ARG = "arg"
        const val SERVICE_CONFIG = "config"
        const val SERVICE_DEBUG = "debug"
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        INSTANCE = this
        applicationAgent.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        autoAppServices().distinct().forEach {
            getService(it)
        }
        applicationAgent.onCreate(this)
    }

    private fun autoServices(): List<String> {
        return listOf(
            SERVICE_CONFIG,
            SERVICE_ARG
        )
    }

    override fun getContext(): Context {
        return this
    }

    open fun autoAppServices(): List<String> = listOf(
        SERVICE_CONFIG,
        SERVICE_ARG
    )

    open fun autoActServices(): List<String> = listOf(
        SERVICE_DEBUG
    )

    open fun createActService(ctx: URContext, key: String): URActService? {
        return when (key) {
            SERVICE_DEBUG -> DebugActService(ctx)
            else -> applicationAgent.createActService(ctx, key)

        }?.apply {
            Log.d(URActService.TAG, "URActService ${this.javaClass.simpleName} created success")
        }
    }

    open fun createAppService(key: String): URService? {
        return when (key) {
            SERVICE_CONFIG -> ConfigAppService(this)
            SERVICE_ARG -> ArgAppService(this)
            else -> applicationAgent.createAppService(this, key)

        }?.apply {
            Log.d(URService.TAG, "URService ${this.javaClass.simpleName} created success")
        }
    }

    override fun getService(key: String): URService {
        var service = services[key]
        if (service == null) {
            service = createAppService(key)
                ?: throw RuntimeException("the URService(key=${key}) not exist")
            services[key] = service
        }
        return service
    }
}

class ApplicationAgent : IApplication {

    private val applicationList by lazy { mutableListOf<IApplication>() }

    override fun attachBaseContext(context: Context) {
        applicationList.addAll(ManifestUtils.parseApplication(context))
        applicationList.forEach {
            it.attachBaseContext(context)
        }
    }

    override fun onCreate(context: Context) {
        applicationList.forEach {
            it.onCreate(context)
        }
    }

    override fun createActService(ctx: URContext, key: String): URActService? {
        for (app in applicationList) {
            val service = app.createActService(ctx, key)
            if (service != null) {
                return service
            }
        }
        return null
    }

    override fun createAppService(ctx: URContext, key: String): URService? {
        for (app in applicationList) {
            val service = app.createAppService(ctx, key)
            if (service != null) {
                return service
            }
        }
        return null
    }
}
