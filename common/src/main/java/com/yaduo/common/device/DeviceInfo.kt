package com.yaduo.common.device

import android.os.Build
import com.yaduo.common.Utils

/**
 * ### 设备工具类
 *  - 获取所需设备信息
 *
 * @author YaDuo
 * @since 2025-05-14 16:11:38
 */
object DeviceInfo {

    /**
     * 获取设备序列号
     */
    fun getUuid(): String = Build.SERIAL

    /**
     * 获取UserId
     */
    fun getUid(): String = Utils.genUid(getUuid())
}