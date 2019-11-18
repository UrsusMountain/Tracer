package com.ursus.base.app.theme.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.ursus.base.app.theme.URTheme
import com.ursus.base.app.theme.URThemeObserver

class URThemeView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), URThemeObserver, URDarkBackground {

    private val themeAttrsDelegate = URThemeAttrsDelegate(this, attrs)

    override fun onThemeChanged(theme: Int) {
        themeAttrsDelegate.onThemeChanged(theme)
    }

    override fun setDarkBackgroundDrawable(drawable: Drawable?) {
        themeAttrsDelegate.setDarkBackgroundDrawable(drawable)
    }

    override fun getTheme() = themeAttrsDelegate.getTheme()
}
