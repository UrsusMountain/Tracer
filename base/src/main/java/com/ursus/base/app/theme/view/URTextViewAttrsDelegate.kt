package com.ursus.base.app.theme.view

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ursus.base.R
import com.ursus.base.app.theme.URTheme

open class URTextViewAttrsDelegate(
    private val textView: TextView,
    attrs: AttributeSet? = null
) : URThemeAttrsDelegate(textView, attrs), URDarkTextColor {

    private var darkTextColor: ColorStateList? = null
    private var lightTextColor: ColorStateList? = null

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.URDarkTheme_TextView)
        darkTextColor = a.getColorStateList(R.styleable.URDarkTheme_TextView_ur_dark_textColor)
            ?: getDefaultDarkTextColor()
        lightTextColor = textView.textColors
        a.recycle()
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

    override fun getDefaultBackground() = ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent))

    fun getDarkTextColor() = darkTextColor ?: getDefaultDarkTextColor()

    fun getLightTextColor() = lightTextColor ?: getDefaultLightTextColor()

    private fun getDefaultDarkTextColor() =
        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.major_text_color_dark))

    private fun getDefaultLightTextColor() =
        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.major_text_color))

}