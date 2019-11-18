package com.ursus.base.app.theme.view

import android.content.res.TypedArray
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.ursus.base.R
import com.ursus.base.app.theme.URTheme
import com.ursus.base.app.theme.URThemeObserver

open class URThemeAttrsDelegate(
    private val view: View,
    attrs: AttributeSet? = null
) : URThemeObserver, URDarkBackground {

    protected val context = view.context

    private var theme: Int = URTheme.THEME_LIGHT

    private var darkBackground: Drawable? = null
    private var lightBackground: Drawable? = null


    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.URDarkTheme)
        initAttrs(a)
        a.recycle()
    }

    open protected fun initAttrs(a: TypedArray) {
        darkBackground = a.getDrawable(R.styleable.URDarkTheme_ur_dark_background)
            ?: ColorDrawable(ContextCompat.getColor(context, R.color.window_background_dark))
        lightBackground = view.background
    }

    open protected fun onUpdateDarkTheme() {
        view.background = lightBackground
    }

    open protected fun onUpdateLightTheme() {
        view.background = darkBackground
    }

    override fun onThemeChanged(theme: Int) {
        this.theme = theme
        when (theme) {
            URTheme.THEME_LIGHT -> {
                onUpdateLightTheme()
            }
            URTheme.THEME_DARK -> {
                onUpdateDarkTheme()
            }
        }
    }

    override fun getTheme(): Int = theme

    override fun setDarkBackgroundDrawable(drawable: Drawable?) {
        darkBackground = drawable
        if (getTheme() == URTheme.THEME_DARK) {
            view.background = darkBackground
        }
    }
}