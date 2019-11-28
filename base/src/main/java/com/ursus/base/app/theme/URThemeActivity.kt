package com.ursus.base.app.theme

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
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
        bindURTheme(window.decorView.findViewById<View>(android.R.id.content))
    }

    override fun onResume() {
        super.onResume()
        if (delayNotifyThemeChanged) {
            delayNotifyThemeChanged = false
            urTheme.setValue(urTheme.getValue())
            onThemeChange(urTheme.getValue())
        }
    }

    override fun onDestroy() {
        urTheme.removeAllObserver()
        super.onDestroy()
    }

    override fun getURTheme(): URTheme = urTheme

    override fun setURThemeValue(value: Int) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            urTheme.setValue(value)
            onThemeChange(value)
        } else {
            delayNotifyThemeChanged = true
        }
    }

    protected fun bindURTheme(view : View) {
        URTheme.bindURThemeView(getURTheme(), view)
    }

    fun setDarkURTheme(isDark: Boolean) {
        setURThemeValue(if (isDark) URTheme.THEME_DARK else URTheme.THEME_LIGHT)
    }

    fun isDarkURTheme() = getURTheme().getValue() == URTheme.THEME_DARK

    open fun createView(@LayoutRes resource: Int, root: ViewGroup?): View {
        val view = layoutInflater.inflate(resource, root, false)
        bindURTheme(view)
        return view
    }

    open fun initNVTheme() = URTheme.THEME_LIGHT

    open fun onThemeChange(theme: Int) {}


}