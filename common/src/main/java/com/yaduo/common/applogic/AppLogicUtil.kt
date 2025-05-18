package com.yaduo.common.applogic

import android.app.Application

/**
 * @author YaDuo
 * time 2025-05-14 15:52:06
 */
object AppLogicUtil {

    private lateinit var sApp: Application

    fun initialize(app: Application) {
        sApp = app
    }

    fun getApp() = sApp
}