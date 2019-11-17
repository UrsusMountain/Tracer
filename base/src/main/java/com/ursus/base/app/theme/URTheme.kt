package com.ursus.base.app.theme

import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.annotation.IntDef
import com.ursus.base.R

class URTheme {

    companion object {
        const val THEME_EMPTY = 0
        const val THEME_LIGHT = 1
        const val THEME_DARK = 2

        @IntDef(THEME_EMPTY, THEME_LIGHT, THEME_DARK)
        @Retention(AnnotationRetention.SOURCE)
        annotation class URThemeValue

        fun bindURThemeView(theme: URTheme, view: View) {
            if (view is URThemeObserver) {
                theme.addObserver(view)
            }
            if (view is ListView) {
                return
            }
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    bindURThemeView(theme, view.getChildAt(i))
                }
            }
        }
    }

    @URThemeValue
    private var value: Int = THEME_EMPTY
    // avoid letting classes other that activity and fragment hold it, otherwise it will cause a memory leak
    private val observerList by lazy { mutableListOf<URThemeObserver>() }

    fun getValue() = value

    fun setValue(@URThemeValue value: Int) {
        if (this.value == value) {
            return
        }
        this.value = value
        notifyThemeChanged()
    }

    fun addObserver(observer: URThemeObserver) {
        if (observerList.contains(observer)) {
            return
        }
        observerList.add(observer)
        if (observer is View) {
            // one observer can only observe one URTheme
            (observer.getTag(R.id._theme_tag) as? URTheme)?.let {
                if (it === this) {
                    return
                }
                it.removeObserver(observer)
            }
            observer.setTag(R.id._theme_tag, value)
        }
        if (value != THEME_EMPTY) {
            observer.onThemeChanged(value)
        }
    }

    fun removeObserver(observer: URThemeObserver) {
        observerList.remove(observer)
        if (observer is View) {
            (observer.getTag(R.id._theme_tag) as? URTheme)?.let {
                if (it === this) {
                    observer.setTag(R.id._theme_tag, null)
                }
            }
        }
    }

    fun removeAllObserver() {
        observerList.forEach {
            if (it is View) {
                it.setTag(R.id._theme_tag, null)
            }
        }
        observerList.clear()
    }

    private fun notifyThemeChanged() = observerList.forEach {
        it.onThemeChanged(value)
    }
}