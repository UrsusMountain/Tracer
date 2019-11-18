package com.ursus.base.app.theme

interface URThemeObserver {
    fun onThemeChanged(theme: Int)
    fun getTheme() : Int
}