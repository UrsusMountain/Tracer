package com.ursus.base.app

import android.content.Context
import com.ursus.base.app.theme.URThemeActivity
import com.ursus.base.service.URActService
import com.ursus.base.service.URService
import com.ursus.base.service.URServiceManager


abstract class URBaseActivity : URThemeActivity(), URContext {

    private val services by lazy { mutableMapOf<String, URService>() }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        (autoServices() + URApplication.INSTANCE.autoActServices()).forEach {
            getService(it)
        }
    }

    override fun getService(key: String): URService {
        var service = services[key]
        if (service == null) {
            service = URServiceManager.createActService(this, key)
                ?: URApplication.INSTANCE.getService(key)
            if (service is URActService) {
                lifecycle.addObserver(service)
            }
            services[key] = service
        }
        return service
    }

    override fun getContext(): Context = this

    open fun autoServices(): List<String> = listOf()

    open fun getStringParam(key: String): String? = intent.getStringExtra(key)

}