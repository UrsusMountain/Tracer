package com.ursus.base.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.ursus.base.app.IApplication
import com.ursus.base.log.Log

object ManifestUtils {

    const val APPLICATION_VALUE = "application_module"

    fun parseApplication(context: Context): List<IApplication> {
        val list = mutableListOf<IApplication>()
        var applicationInfo : ApplicationInfo ?= null
        try {
            applicationInfo = context.packageManager.getApplicationInfo(
                context.packageName,
                PackageManager.GET_META_DATA
            )
        } catch (e : Exception) {
        }
        applicationInfo?.metaData?.let {
            for (key in it.keySet()) {
                if (APPLICATION_VALUE == it.get(key)) {
                    try {
                        val applicationClazz = Class.forName(key)
                        val application = applicationClazz.newInstance()
                        if ((application is IApplication)) {
                            list.add(application)
                        }
                    } catch (e: Exception) {
                        Log.d("ManifestUtils", "parse application error : ${e.message}")
                    }
                }
            }
        }
        return list
    }
}