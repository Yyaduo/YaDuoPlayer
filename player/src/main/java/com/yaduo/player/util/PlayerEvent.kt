package com.yaduo.player.util

/**
 * 播放器事件
 *
 * @author YaDuo
 * @since 2025-07-19 15:44:28
 */

/**
 * 播放模式变更事件
 * @param mode 播放模式值
 */
data class PlayModeEventData(val mode: Int)

/**
 * 播放控制变更事件
 * @param mode 播放控制值
 */
data class PlayControlEventData(val mode: Int)