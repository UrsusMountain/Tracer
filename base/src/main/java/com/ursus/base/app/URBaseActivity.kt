package com.ursus.base.app

import com.ursus.base.app.theme.URThemeActivity


open abstract class URBaseActivity : URThemeActivity() {

    fun getStringParam(key: String): String? = intent.getStringExtra(key)

}