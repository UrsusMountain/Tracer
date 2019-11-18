package com.ursus.base.app.theme

interface URThemeOwner {
    fun getURTheme(): URTheme
    fun setURThemeValue(@URTheme.Companion.URThemeValue value: Int)
}