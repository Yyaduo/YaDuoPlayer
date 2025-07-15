package com.yaduo.player.interfaces

import androidx.media3.common.Player
import com.yaduo.player.model.MediaItemData

/**
 * 播放器抽象接口
 *
 * @author YaDuo
 * @since 2025-05-18 02:30:36
 */
interface IPlayer {

    /**
     * 播放传入的MediaItem
     * @param mediaItem 要播放的MediaItem
     */
    fun play(mediaItem: MediaItemData)

    /**
     *  返回当前正在使用的MediaItem，不代表正在播放，只是player中选取了该单位
     *  @return 现在正被player选取的MediaItem
     */
    fun getCurrentMedia(): MediaItemData

    /**
     * 暂停播放
     */
    fun pause()

    /**
     * 跳转到指定播放位置
     * @param position 目标位置（单位：毫秒），需满足0 ≤ position ≤ [getDuration]
     */
    fun seekTo(position: Long)

    /**
     * 获取当前媒体总时长
     * @return 媒体时长
     */
    fun getDuration(): Long

    /**
     * 获取当前播放位置
     * @return 当前位置
     */
    fun getCurrentPosition(): Long

    /**
     * 检查播放器是否正在播放
     * @return 正在播放则为true
     */
    fun isPlaying(): Boolean

    /**
     * 重置播放器至初始状态
     *
     * **实现要求**：
     * - 停止播放并释放资源
     * - 当前使用的MediaItem置空
     * - 保留监听器列表
     */
    fun reset()

    /**
     * 释放播放器占用的所有资源
     *
     * **生命周期规则**：
     * - 调用后播放器实例不可复用
     * - 必须清空所有监听器
     * - 必须调用底层播放器的release方法
     */
    fun release()

    /**
     * 获取播放器实例
     */
    fun getPlayer(): Player

    /**
     * 添加一个播放监听器
     */
    fun addPlayerListener(playerListener: PlayerListener)

    /**
     * 移除一个播放监听器
     */
    fun removePlayerListener(playerListener: PlayerListener)
}