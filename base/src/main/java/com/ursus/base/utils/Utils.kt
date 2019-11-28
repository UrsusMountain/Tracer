package com.ursus.base.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.ursus.base.app.URApplication

fun <T : View> Activity.bind(@IdRes res: Int): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }

fun <T : View> Fragment.bind(@IdRes res: Int): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { view!!.findViewById<T>(res) }

object Utils {

    fun showToast(context: Context?, message: String) =
        Toast.makeText(context ?: URApplication.INSTANCE, message, Toast.LENGTH_SHORT).show()

    fun showToast(context: Context?, messageRes: Int) =
        Toast.makeText(context ?: URApplication.INSTANCE, messageRes, Toast.LENGTH_SHORT).show()

    fun showToast(message: String) = showToast(null, message)

    fun showToast(messageRes: Int) = showToast(null, messageRes)

}