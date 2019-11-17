package com.ursus.base.app

import android.content.Context

interface IApplication {
    fun attachBaseContext(context: Context)
    fun onCreate(context: Context)
}