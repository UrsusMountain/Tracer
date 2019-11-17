package com.ursus.base.app

import android.content.Context
import android.content.ContextWrapper

interface URContext {
    fun getContext(): Context
}


fun Context?.getURContext() : URContext? {
    var ctx = this
    for (i in 0..5) {
        if (ctx is URContext) {
            return ctx
        }
        if (ctx is ContextWrapper) {
            val base = ctx.baseContext
            if (base == null || base === this) {
                return null
            }
            ctx = base
        }
    }
    return null
}