package com.ursus.base.app.theme

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle

abstract class URThemeActivity : FragmentActivity(), URThemeOwner {

    private val urTheme: URTheme = URTheme()
    private var delayNotifyThemeChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setURThemeValue(if (urTheme.getValue() == URTheme.THEME_EMPTY) initNVTheme() else urTheme.getValue())
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        URTheme.bindURThemeView(getURTheme(), window.decorView.findViewById<View>(android.R.id.content))
    }


    override fun onStart() {
        super.onStart()
        if (delayNotifyThemeChanged) {
            delayNotifyThemeChanged = false
            onThemeChange(urTheme.getValue())
        }
    }

    override fun onDestroy() {
        urTheme.removeAllObserver()
        super.onDestroy()
    }

    override fun getURTheme(): URTheme = urTheme

    override fun setURThemeValue(value: Int) {
        urTheme.setValue(value)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            onThemeChange(value)
        } else {
            delayNotifyThemeChanged = true
        }
    }

    fun setDarkNVTheme(isDark: Boolean) {
        setURThemeValue(if (isDark) URTheme.THEME_DARK else URTheme.THEME_LIGHT)
    }

    open fun onThemeChange(theme: Int) {}

    open fun initNVTheme(): Int {
        return URTheme.THEME_LIGHT
    }
}