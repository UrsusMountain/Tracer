package com.ursus.base.app

import android.os.Bundle
import com.ursus.base.app.theme.URThemeActivity

open abstract class URBaseActivity : URThemeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        updateView()
    }

    abstract fun updateView()

    abstract fun layoutId(): Int
}