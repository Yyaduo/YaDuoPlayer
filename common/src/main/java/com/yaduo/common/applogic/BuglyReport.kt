package com.yaduo.common.applogic

import android.os.Build
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import com.yaduo.common.ApplicationVersion
import com.yaduo.common.device.DeviceInfo

/**
 * @author YaDuo
 * @since 2025-05-14 16:42:06
 *
 * @see <b><i> bugly.qq.com <i><b>
 */
object BuglyReport {

    /**
     * ## 初始化 Bugly 崩溃报告系统
     *
     * 此方法用于配置并启动 Bugly SDK，用于收集应用崩溃日志和异常信息。
     *
     * ### 参数说明
     *
     * Bugly SDK 初始化需要以下参数：
     *
     * 1. **ApplicationContext**
     *    应用上下文对象
     * 2. **APPID**
     *    在 Bugly 平台注册时申请的应用程序 ID
     * 3. **调试模式开关**
     *    控制 SDK 的调试行为：
     *    - 输出详细的 Bugly SDK 日志
     *    - 每条崩溃日志都会立即上报
     *    - 自定义日志将在 Logcat 中输出
     * 4. **用户策略对象**
     *    可配置以下设备和应用信息：
     *    - `deviceID`: 设备唯一标识符
     *    - `deviceModel`: 设备型号
     *    - `appVersion`: 应用版本名称
     *    - `appPackageName`: 应用包名
     *    - 初始化延迟
     *    - ANR 跟踪采集等高级配置
     *
     * ### 初始化流程
     *
     * 1. 创建用户策略对象
     * 2. 配置设备和应用信息
     * 3. 初始化 Bugly SDK
     * 4. 设置用户 ID 用于跟踪
     *
     * ### 使用示例
     *
     * ```kotlin
     * // 在 Application 类中初始化
     * class App : Application() {
     *     override fun onCreate() {
     *         super.onCreate()
     *         BuglyReport.initialize()
     *     }
     * }
     * ```
     *
     * ### 注意事项
     *
     * - 必须在主线程调用此方法
     * - 建议在 Application 的 onCreate() 中尽早调用
     * - 调试模式下会输出详细日志，发布版本应关闭调试模式
     *
     * @see [Bugly 官方文档](https://bugly.qq.com/docs/)
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