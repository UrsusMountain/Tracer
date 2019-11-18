package com.ursus.base.developer

import android.widget.Switch
import com.ursus.base.R
import com.ursus.base.app.URBaseActivity

class DeveloperActivity : URBaseActivity() {

    override fun layoutId(): Int = R.layout.activity_developer

    override fun updateView() {
        val swTheme = findViewById<Switch>(R.id.sw_theme)
        swTheme.setOnCheckedChangeListener { _, isChecked ->
            setDarkURTheme(isChecked)
        }
    }

}