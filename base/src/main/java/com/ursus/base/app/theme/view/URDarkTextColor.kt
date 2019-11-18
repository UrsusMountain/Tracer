package com.ursus.base.app.theme.view

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

interface URDarkTextColor {
    fun setDarkTextColor(color: ColorStateList?)
    fun setDarkTextColor(@ColorInt color: Int)
}