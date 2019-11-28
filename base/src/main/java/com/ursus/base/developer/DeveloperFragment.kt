package com.ursus.base.developer

import android.os.Bundle
import android.view.View
import android.widget.Switch
import com.ursus.base.R
import com.ursus.base.app.URBaseFragment
import com.ursus.base.utils.bind

class DeveloperFragment : URBaseFragment() {

    val swTheme by bind<Switch>(R.id.sw_theme)

    override fun layoutId() = R.layout.fragment_developer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swTheme.setOnCheckedChangeListener { _, isChecked ->
            setDarkURTheme(isChecked, true)
        }
    }
}