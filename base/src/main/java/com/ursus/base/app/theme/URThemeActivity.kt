package com.ursus.base.app.theme

import androidx.fragment.app.FragmentActivity

class URThemeActivity : FragmentActivity() , URThemeOwner{

    private val urTheme: URTheme = URTheme()
    
    override fun getURTheme(): URTheme = urTheme

    override fun setURThemeValue(themeValue: Int) {

    }
}