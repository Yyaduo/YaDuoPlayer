package com.yaduo.yaduoplayer

import android.app.Application
import android.util.Log
import com.yaduo.common.applogic.AppLogicUtil
import com.yaduo.common.applogic.BuglyReport

/**
 * @author YaDuo
 * time 2025-05-14 15:35:09
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
        BuglyReport.initialize()
        Log.i(TAG, "onCreate: application begin")
    }

}