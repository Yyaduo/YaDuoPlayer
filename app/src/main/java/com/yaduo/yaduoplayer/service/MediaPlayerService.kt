package com.yaduo.yaduoplayer.service

import android.content.Context
import android.content.Intent
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerNotificationManager.ACTION_PAUSE
import androidx.media3.ui.PlayerNotificationManager.ACTION_PLAY
import com.yaduo.common.applogic.AppLogicUtil
import com.yaduo.common.log.LogUtil
import com.yaduo.player.impl.ExoPlayerImpl
import com.yaduo.player.interfaces.IPlayer
import com.yaduo.player.service.BasePlayerService
import com.yaduo.player.util.PlayModeEventData
import com.yaduo.player.util.toPlayModeString
import com.yaduo.yaduoplayer.MainActivity
import com.yaduo.yaduoplayer.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author YaDuo
 * @since 2025-05-23 20:07:56
 */
@OptIn(UnstableApi::class)
class MediaPlayerService : BasePlayerService<MainActivity>() {

    companion object {
        private const val TAG = "MediaPlayerService"

        /**
         * 启动播放服务
         * @param context 传入的上下文，用于启动前台服务
         */
        fun launchMediaPlayerService(context: Context) {
            runCatching {
                context.startForegroundService(Intent(context, MediaPlayerService::class.java))
            }.onFailure {
                LogUtil.e(TAG, "error, message = ${it.message}")
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    /**
     * 处理播放模式变更事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPlayModeEvent(event: PlayModeEventData) {
        LogUtil.d(TAG, "play mode = ${event.mode.toPlayModeString()}")
        player.setPlayMode(event.mode)
    }

    /**
     * 获取当前播放模式
     */
    fun getCurrentPlayMode() = player.getPlayMode()

    override fun createPlayer(): IPlayer = ExoPlayerImpl(AppLogicUtil.getApp())

    override fun getActivityClass() = MainActivity::class.java

    override fun getNotificationIcon() = R.drawable.ic_player_logo

    // fixme: 待修复
    override fun getNotificationContentTitle() = "player.getCurrentMedia().title"

    override fun getPlayActionIcon() = R.drawable.ic_player_logo

    override fun getPauseActionIcon() = R.drawable.ic_player_logo

    override fun handleMediaAction(action: String?) {
        when (action) {
            ACTION_PLAY -> {
                if (player.isPlaying().not()) {
                    // fixme: 待修复
//                    player.play(player.getCurrentMedia())
                }
            }

            ACTION_PAUSE -> {
                if (player.isPlaying()) {
                    player.pause()
                }
            }

            else -> {}
        }
        updateNotification()
    }

}