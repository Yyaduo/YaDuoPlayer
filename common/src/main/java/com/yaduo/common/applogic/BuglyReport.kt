package com.yaduo.common.applogic

import android.os.Build
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import com.yaduo.common.ApplicationVersion
import com.yaduo.common.device.DeviceInfo

/**
 * @author YaDuo
 * time 2025-05-14 16:42:06
 *
 * @see "bugly.qq.com"
 */
object BuglyReport {

    /**
     * 初始化CrashReport
     * 参数如下：
     * 1、ApplicationContext
     * 2、注册时申请的APPID
     * 3、SDK调试模式开关，调试模式的行为特性如下：
     *  3.1、输出详细的Bugly SDK的Log
     *  3.2、每一条Crash都会被立即上报
     *  3.3、自定义日志将会在Logcat中输出
     * 4、用户策略，可配置设备Id、设备型号、App版本、渠道、包名、初始化延迟、anr trace采集等
     */
    fun initialize() {

        // 用户策略
        val userStrategy = UserStrategy(AppLogicUtil.getApp()).apply {
            deviceID = DeviceInfo.getUuid()
            deviceModel = Build.MODEL
            appVersion = ApplicationVersion.APP_NAME
            appPackageName = ApplicationVersion.PACKAGE_NAME
        }

        // 初始化
        CrashReport.initCrashReport(
            AppLogicUtil.getApp(),
            "289dd65d41",
            false,
            userStrategy
        )

        // 额外配置uid
        CrashReport.setUserId(DeviceInfo.getUid())
    }

}