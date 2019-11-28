package com.ursus.base.log

interface Logger {
    fun log(level: Int, tag: String, msg: String, e: Throwable?)
}