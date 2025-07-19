@file:JvmName("PlayerUtil")

package com.yaduo.player.util

import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import com.yaduo.player.interfaces.PlayerListener
import com.yaduo.player.model.MediaItemData
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @author YaDuo
 * @since 2025-05-22 18:01:35
 *
 * 播放器工具类
 */


/**
 * 解析播放状态
 * @param listeners 元素是[PlayerListener]的列表，当传入播放状态时，根据状态不同去回调接口的方法
 */
fun Player.parsePlaybackState(listeners: CopyOnWriteArrayList<PlayerListener>): Any {
    return when (this.playbackState) {
        Player.STATE_IDLE -> "STATE_IDLE"

        Player.STATE_BUFFERING -> {
            listeners.forEach { it.onBuffering() }
            return "STATE_BUFFERING"
        }

        Player.STATE_READY -> {
            listeners.forEach { it.onReady() }
            return "STATE_READY"
        }

        Player.STATE_ENDED -> {
            listeners.forEach { it.onEnded() }
            return "STATE_ENDED"
        }

        else -> "UNKNOWN_STATE"
    }
}

/**
 * 将[MediaItemData]转换成[MediaItem]
 */
fun MediaItemData.toMediaItem(): MediaItem =
    MediaItem.Builder()
        .setUri(this.uri)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(this.title)
                .setArtist(this.artist)
                .build()
        )
        .setTag(this)
        .build()

/**
 * 将 Int 值转换成播放模式的 String 表示
 */
fun Int.toPlayModeString(): String {
    return when (this) {
        Player.REPEAT_MODE_OFF -> "REPEAT_MODE_OFF"
        Player.REPEAT_MODE_ONE -> "REPEAT_MODE_ONE"
        Player.REPEAT_MODE_ALL -> "REPEAT_MODE_ALL"
        else -> "UNKNOWN_PLAY_MODE"
    }
}