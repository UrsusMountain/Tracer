package com.ursus.base.app.theme

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

abstract class URThemeFragment : Fragment(), URThemeOwner {

    private val urTheme: URTheme = URTheme()
    private var nvThemeObserver: URThemeObserver? = null
    private var delayNotifyThemeChanged = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (useParentURTheme() && (activity is URThemeOwner)) {
            val themeOwner = activity as URThemeOwner
            if (nvThemeObserver == null) {
                nvThemeObserver = object : URThemeObserver {
                    override fun onThemeChanged(theme: Int) {
                        setURThemeDirect(theme)
                    }

                    override fun getTheme() = urTheme.getValue()
                }
            }
            themeOwner.getURTheme().addObserver(nvThemeObserver!!)
            setURThemeDirect(themeOwner.getURTheme().getValue())
        } else {
            setURThemeDirect(if (urTheme.getValue() == URTheme.THEME_EMPTY) initNVTheme() else urTheme.getValue())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindURTheme(view)
    }

    override fun onResume() {
        super.onResume()
        if (delayNotifyThemeChanged) {
            delayNotifyThemeChanged = false
            urTheme.setValue(urTheme.getValue())
            onThemeChange(urTheme.getValue())
        }
    }

    override fun onDestroyView() {
        urTheme.removeAllObserver()
        super.onDestroyView()
    }

    override fun getURTheme(): URTheme = urTheme

    override fun setURThemeValue(value: Int) {
        setURThemeValue(value, false)
    }

    protected fun bindURTheme(view: View) {
        URTheme.bindURThemeView(getURTheme(), view)
    }

    fun setURThemeValue(@URTheme.Companion.URThemeValue value: Int, associateActivity: Boolean) {
        if (useParentURTheme() && activity is URThemeOwner) {
            return
        }
        if (associateActivity && activity is URThemeOwner) {
            val themeOwner = activity as URThemeOwner
            themeOwner.setURThemeValue(value)
        }
        setURThemeDirect(value)
    }

    private fun setURThemeDirect(@URTheme.Companion.URThemeValue value: Int) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            urTheme.setValue(value)
            onThemeChange(value)
        } else {
            delayNotifyThemeChanged = true
        }
    }

    fun setDarkURTheme(isDark: Boolean) {
        setDarkURTheme(isDark, false)
    }

    fun setDarkURTheme(isDark: Boolean, associateActivity: Boolean) {
        setURThemeValue(if (isDark) URTheme.THEME_DARK else URTheme.THEME_LIGHT, associateActivity)
    }

    open fun useParentURTheme() = false

    open fun onThemeChange(theme: Int) {}

    open fun initNVTheme() = URTheme.THEME_LIGHT

}