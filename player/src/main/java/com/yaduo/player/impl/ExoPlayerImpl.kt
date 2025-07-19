package com.yaduo.player.impl

import android.content.Context
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.yaduo.common.log.LogUtil
import com.yaduo.player.util.parsePlaybackState

/**
 * 实现了[androidx.media3.exoplayer.ExoPlayer]的播放器
 * 继承于[BasePlayer]，在这里只需要实现创建播放器和监听器即可
 *
 * @author YaDuo
 * @since 2025-05-22 17:00:15
 */
@UnstableApi
class ExoPlayerImpl(
    private var context: Context,
    private val config: ExoPlayerConfig = ExoPlayerConfig()
) : BasePlayer() {

    companion object {
        private const val TAG = "ExoPlayerImpl"
    }

    init {
        innerPlayer.addListener(createPlayerListener())
    }

    override fun createPlayer(): Player {
        return ExoPlayer.Builder(context)
            .setLoadControl(config.loadControl)
            .setTrackSelector(config.trackSelector)
            .build()
    }

    /**
     * 创建一个播放器监听器
     * 其中的事件会通过播放器内置的监听器列表传递给外部监听者
     */
    private fun createPlayerListener(): Player.Listener {
        return object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) =
                LogUtil.w(
                    TAG,
                    "onPlaybackStateChanged -> playbackState =  ${
                        innerPlayer.parsePlaybackState(listeners)
                    }"
                )

            override fun onPlayerError(error: PlaybackException) =
                listeners.forEach { it.onError(error) }

            override fun onEvents(player: Player, events: Player.Events) {
                if (events.contains(Player.EVENT_REPEAT_MODE_CHANGED)) {
                    notifyPlayModeChanged(player.repeatMode)
                }
            }
        }
    }
}