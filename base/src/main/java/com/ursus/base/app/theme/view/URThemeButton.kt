package com.ursus.base.app.theme.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView
import com.ursus.base.app.theme.URTheme
import com.ursus.base.app.theme.URThemeObserver

class URThemeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Button(context, attrs, defStyleAttr), URThemeObserver, URDarkBackground, URDarkTextColor {

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
