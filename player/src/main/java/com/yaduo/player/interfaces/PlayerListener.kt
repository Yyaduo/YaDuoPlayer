package com.yaduo.player.interfaces

import androidx.media3.common.PlaybackException

/**
 * @author YaDuo
 * @since 2025-05-22 17:03:19
 */
interface PlayerListener {
    fun onReady()

    fun onBuffering()

    fun onEnded()

    fun onError(error: PlaybackException)
}