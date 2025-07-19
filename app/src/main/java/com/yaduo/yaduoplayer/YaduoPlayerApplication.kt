package com.yaduo.yaduoplayer

import android.app.Application
import com.yaduo.common.applogic.AppLogicUtil
import com.yaduo.common.log.LogUtil

/**
 * @author YaDuo
 * @since 2025-05-14 15:35:09
 */
class YaduoPlayerApplication : Application() {

    companion object {
        private const val TAG = "YaduoPlayerApplication"
        private var startAppTime = 0L
        fun getStartAppTime() = startAppTime
    }

    override fun onCreate() {
        super.onCreate()
        startAppTime = System.currentTimeMillis()
        AppLogicUtil.initialize(this)
        AppLogicUtil.initializeAllCommonModule()
        LogUtil.i(TAG, "onCreate: application begin: ${AppLogicUtil.getApplicationVersionInfo()}")
    }

}