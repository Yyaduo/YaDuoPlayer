package com.yaduo.player.impl

import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.yaduo.player.interfaces.IPlayer
import com.yaduo.player.interfaces.PlayModeListener
import com.yaduo.player.interfaces.PlayerListener
import com.yaduo.player.model.MediaItemData
import com.yaduo.player.util.toMediaItem
import java.util.concurrent.CopyOnWriteArrayList

/**
 * ### 播放器基类
 * 实现了[IPlayer]接口，定义了播放器的通用行为和生命周期管理
 *
 * @author YaDuo
 * @since 2025-05-24 15:08:58
 */
@UnstableApi
abstract class BasePlayer : IPlayer {

    /** 播放器监听器列表，一个播放器可能有多个地方在监听 **/
    protected val listeners = CopyOnWriteArrayList<PlayerListener>()

    /** 播放模式变更监听器列表 **/
    private val playModeListeners = CopyOnWriteArrayList<PlayModeListener>()

    /** 当前正在使用的MediaItem，不代表正在播放，只是player中选取了该单位 **/
    private var currentMediaItem: MediaItemData? = null

    /** 播放器实例，由子类实现createPlayer方法后创建 **/
    protected val innerPlayer by lazy { createPlayer() }

    override fun play(mediaItemData: MediaItemData) {
        currentMediaItem = mediaItemData
        innerPlayer.apply {
            setMediaItem(mediaItemData.toMediaItem())
            prepare()
            playWhenReady = true
            play()
        }
    }

    override fun getCurrentMedia(): MediaItemData =
        currentMediaItem ?: throw IllegalArgumentException("No Media Item Using")

    override fun reset() {
        innerPlayer.stop()
        innerPlayer.clearMediaItems()
        currentMediaItem = null
    }

    override fun release() {
        removeAllListener()
        innerPlayer.release()
    }

    override fun pause() = innerPlayer.pause()

    override fun seekTo(position: Long) = innerPlayer.seekTo(position)

    override fun getDuration() = innerPlayer.duration

    override fun getCurrentPosition() = innerPlayer.currentPosition

    override fun isPlaying() = innerPlayer.isPlaying

    override fun getPlayer() = innerPlayer

    override fun setPlayMode(playMode: Int) {
        innerPlayer.repeatMode = playMode
        notifyPlayModeChanged(playMode)
    }

    override fun getPlayMode(): Int = innerPlayer.repeatMode

    override fun addPlayModeListener(listener: PlayModeListener) {
        playModeListeners.add(listener)
    }

    override fun removePlayModeListener(listener: PlayModeListener) {
        playModeListeners.remove(listener)
    }

    override fun addPlayerListener(playerListener: PlayerListener) {
        listeners.add(playerListener)
    }

    override fun removePlayerListener(playerListener: PlayerListener) {
        listeners.remove(playerListener)
    }

    internal fun notifyPlayModeChanged(newMode: Int) {
        playModeListeners.forEach { it.onPlayModeChanged(newMode) }
    }

    private fun removeAllListener() {
        listeners.clear()
        playModeListeners.clear()
    }

    /**
     * 创建播放器实例
     * 由子类实现
     */
    abstract fun createPlayer(): Player

}