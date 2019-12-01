package com.ursus.base.service

import com.ursus.base.app.URContext

abstract class URService(val ctx :URContext) {
    companion object {
        const val TAG = "URService"
    }
}