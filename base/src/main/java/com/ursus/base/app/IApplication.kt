package com.ursus.base.app

import android.content.Context
import com.ursus.base.service.URActService
import com.ursus.base.service.URService

interface IApplication {
    fun attachBaseContext(context: Context)
    fun onCreate(context: Context)
    fun createActService(ctx: URContext, key: String) : URActService?
    fun createAppService(ctx: URContext, key: String) : URService?
}