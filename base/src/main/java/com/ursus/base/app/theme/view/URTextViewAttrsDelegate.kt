package com.ursus.base.app.theme.view

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ursus.base.R
import com.ursus.base.app.theme.URTheme

class URTextViewAttrsDelegate(
    private val textView: TextView,
    attrs: AttributeSet? = null
) : URThemeAttrsDelegate(textView, attrs), URDarkTextColor {

    private var darkTextColor: ColorStateList? = null
    private var lightTextColor: ColorStateList? = null

    override fun initAttrs(a: TypedArray) {
        super.initAttrs(a)
        darkTextColor = a.getColorStateList(R.styleable.URDarkTheme_ur_dark_textColor)
            ?: getDefaultDarkTextColor()
        lightTextColor = textView.textColors
    }

    override fun onUpdateDarkTheme() {
        super.onUpdateDarkTheme()
        textView.setTextColor(getDarkTextColor())
    }

    override fun onUpdateLightTheme() {
        super.onUpdateLightTheme()
        textView.setTextColor(getLightTextColor())
    }

    override fun setDarkTextColor(color: ColorStateList?) {
        darkTextColor = color
        if (getTheme() == URTheme.THEME_DARK) {
            textView.setTextColor(getDarkTextColor())
        }
    }

    override fun setDarkTextColor(color: Int) {
        setDarkTextColor(ColorStateList.valueOf(color))
    }

    fun getDarkTextColor() = darkTextColor ?: getDefaultDarkTextColor()

    fun getLightTextColor() = lightTextColor ?: getDefaultLightTextColor()

    private fun getDefaultDarkTextColor() =
        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.major_text_color_dark))

    private fun getDefaultLightTextColor() =
        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.major_text_color))

}