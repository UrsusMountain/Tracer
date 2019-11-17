package com.ursus.base.app

import android.app.Application
import android.content.Context
import com.ursus.base.utils.ManifestUtils

fun URApplication.getInstance(): URApplication = this

abstract class URApplication : Application(), URContext {

    private val applicationAgent by lazy { ApplicationAgent() }

    companion object {
        lateinit var INSTANCE : URApplication
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        INSTANCE = this
        applicationAgent.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        applicationAgent.onCreate(this)
    }

    override fun getContext(): Context {
        return this
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

}
