package com.yaduo.common.applogic

import android.app.Application

/**
 * @author YaDuo
 * @since 2025-05-14 15:52:06
 */
object AppLogicUtil {

    private lateinit var sApp: Application

    /**
     * 初始化方法，传入Application的实例
     * @param app 在Application创建时传入上下文
     */
    fun initialize(app: Application) {
        sApp = app
    }

    /**
     * 获取Application实例
     */
    fun getApp() = sApp
}