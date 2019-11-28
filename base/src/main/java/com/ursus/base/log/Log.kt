package com.ursus.base.log

import com.ursus.base.BuildConfig


object Log {

    const val TRACER = "tracer"

    private val V = BuildConfig.DEBUG
    private val D = V
    private val I = D
    private val W = I
    private val E = W

    val loggers by lazy { mutableListOf<Logger>() }

    fun v(tag: String, msg: String) {
        if (V) {
            android.util.Log.v(tag, msg)
        }
        for (logger in loggers) {
            logger.log(android.util.Log.VERBOSE, tag, msg, null)
        }
    }

    fun v(msg: String) {
        v(TRACER, msg)
    }

    fun d(tag: String, msg: String) {
        if (D) {
            android.util.Log.d(tag, msg)
        }
        for (logger in loggers) {
            logger.log(android.util.Log.DEBUG, tag, msg, null)
        }
    }

    fun d(msg: String) {
        d(TRACER, msg)
    }

    fun i(tag: String, msg: String, err: Throwable?) {
        if (I) {
            android.util.Log.i(tag, msg, err)
        }
        for (logger in loggers) {
            logger.log(android.util.Log.INFO, tag, msg, err)
        }
    }

    fun i(tag: String, msg: String) {
        i(tag, msg, null)
    }

    fun i(msg: String, err: Throwable?) {
        i(TRACER, msg, err)
    }

    fun i(msg: String) {
        i(TRACER, msg)
    }

    fun w(tag: String, msg: String, err: Throwable?) {
        if (W) {
            android.util.Log.w(tag, msg, err)
        }
        for (logger in loggers) {
            logger.log(android.util.Log.WARN, tag, msg, err)
        }
    }

    fun w(tag: String, msg: String) {
        w(tag, msg, null)
    }

    fun w(msg: String, err: Throwable?) {
        w(TRACER, msg, err)
    }

    fun w(msg: String) {
        w(TRACER, msg)
    }

    fun e(tag: String, msg: String, err: Throwable?) {
        if (E) {
            android.util.Log.e(tag, msg, err)
        }
        for (logger in loggers) {
            logger.log(android.util.Log.ERROR, tag, msg, err)
        }
    }

    fun e(tag: String, msg: String) {
        e(tag, msg, null)
    }

    fun e(msg: String, err: Throwable?) {
        e(TRACER, msg, err)
    }

    fun e(msg: String) {
        e(TRACER, msg)
    }
}