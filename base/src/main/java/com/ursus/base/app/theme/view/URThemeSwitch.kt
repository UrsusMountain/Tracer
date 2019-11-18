package com.ursus.base.app.theme.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.Switch
import com.ursus.base.app.theme.URThemeObserver

class URThemeSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.switchStyle,
    defStyleRes: Int = 0
) : Switch(context, attrs, defStyleAttr, defStyleRes), URThemeObserver, URDarkBackground, URDarkTextColor {

    private val textAttrsDelegate = URTextViewAttrsDelegate(this, attrs)

    override fun onThemeChanged(theme: Int) {
        textAttrsDelegate.onThemeChanged(theme)
    }

    override fun getTheme(): Int = textAttrsDelegate.getTheme()

    override fun setDarkBackgroundDrawable(drawable: Drawable?) {
        textAttrsDelegate.setDarkBackgroundDrawable(drawable)
    }

    override fun setDarkTextColor(color: ColorStateList?) {
        textAttrsDelegate.setDarkTextColor(color)
    }

    override fun setDarkTextColor(color: Int) {
        textAttrsDelegate.setDarkTextColor(color)
    }

}
