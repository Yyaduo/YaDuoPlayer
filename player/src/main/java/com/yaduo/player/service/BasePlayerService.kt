package com.yaduo.player.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Process
import androidx.annotation.OptIn
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media3.common.PlaybackException
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.legacy.MediaSessionCompat
import androidx.media3.ui.PlayerNotificationManager.ACTION_PAUSE
import androidx.media3.ui.PlayerNotificationManager.ACTION_PLAY
import com.yaduo.common.ApplicationVersion.APP_NAME
import com.yaduo.common.log.LogUtil
import com.yaduo.player.interfaces.IPlayer
import com.yaduo.player.interfaces.PlayerListener
import com.yaduo.player.util.PlayerConstant.ACTION_DEFAULT
import com.yaduo.player.util.PlayerConstant.ACTION_PAUSE_MEDIA
import com.yaduo.player.util.PlayerConstant.ACTION_PLAY_MEDIA
import com.yaduo.player.util.PlayerConstant.NOTIFICATION_CHANNEL_ID

/**
 * ### 基础播放服务
 *  - 通知栏控制
 *  - 前台服务保活
 *
 * @author YaDuo
 * @since 2025-05-23 11:44:53
 *
 * @param T 关联的Activity
 */
@OptIn(UnstableApi::class)
abstract class BasePlayerService<T : Any> : Service() {

    /** 播放器接口实例，具体由子类提供 **/
    protected lateinit var player: IPlayer

    /** 通知构建器，用于构建控制组件通知 **/
    private lateinit var notificationBuilder: NotificationCompat.Builder

    /** 通知ID **/
    private val notificationId: Int = Process.myPid()

    /** 播放状态监听器 **/
    private val playerListener = object : PlayerListener {

        override fun onReady() = updateNotification()

        override fun onBuffering() {
            TODO("Not yet implemented")
        }

        override fun onEnded() = stopForeground(STOP_FOREGROUND_REMOVE)

        override fun onError(error: PlaybackException) {
            LogUtil.e(this.toString(), "message = ${error.message}")
            stopForeground(STOP_FOREGROUND_REMOVE)
        }
    }

    /**
     * 创建播放器，由子类实现
     * @return 播放器实例
     */
    abstract fun createPlayer(): IPlayer

    /**
     * 获取Activity实例，由子类实现
     * @return Activity实例
     */
    abstract fun getActivityClass(): Class<T>

    /**
     * 获取通知小图标，由子类实现
     * @return 图标的resourceID
     */
    abstract fun getNotificationIcon(): Int

    /**
     * 获取通知主标题，由子类实现
     * @return 通知主标题
     */
    abstract fun getNotificationContentTitle(): String

    /**
     * 获取播放按钮图标，由子类实现
     * @return 播放按钮的resourceID
     */
    abstract fun getPlayActionIcon(): Int

    /**
     * 获取暂停按钮图标，由子类实现
     * @return 暂停按钮的resourceID
     */
    abstract fun getPauseActionIcon(): Int

    /**
     * 处理媒体控制行为
     * @param action 来自通知栏的控制行为
     */
    abstract fun handleMediaAction(action: String?)

    /**
     * 在服务创建时创建播放器，并添加监听器
     * 最后显示通知
     */
    override fun onCreate() {
        super.onCreate()
        player = createPlayer()
        player.addPlayerListener(playerListener)
        initNotificationAndShow()
        registerMediaSession()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let { handleMediaAction(it.action) }
        return START_STICKY
    }

    override fun onDestroy() {
        player.removePlayerListener(playerListener)
        player.release()
        stopForeground(STOP_FOREGROUND_REMOVE)
        super.onDestroy()
    }

    /**
     * 初始化一个通知并显示在状态栏
     * 当服务拉起来时，创建这个通知用于显示当前的媒体信息
     */
    private fun initNotificationAndShow() {
        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            APP_NAME,
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "$APP_NAME is running"
            lockscreenVisibility = Notification.VISIBILITY_SECRET
        }
        NotificationManagerCompat.from(this).createNotificationChannel(notificationChannel)

        notificationBuilder = buildBaseNotification()

        startForeground(notificationId, notificationBuilder.build())
    }

    /**
     * 初始化通知构建器
     */
    private fun buildBaseNotification(): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(getNotificationIcon())
            .setContentTitle(getNotificationContentTitle())
            .setContentIntent(buildContentIntent())
            .addAction(createPlayAction())
            .addAction(createPauseAction())
            .setGroup(NOTIFICATION_CHANNEL_ID)
            .setDefaults(Notification.FLAG_FOREGROUND_SERVICE)
    }

    /**
     * 创建播放动作
     */
    private fun createPlayAction(): NotificationCompat.Action {
        return NotificationCompat.Action(
            getPlayActionIcon(),
            ACTION_PLAY_MEDIA,
            buildContentIntent(ACTION_PLAY)
        )
    }

    /**
     * 创建暂停动作
     */
    private fun createPauseAction(): NotificationCompat.Action {
        return NotificationCompat.Action(
            getPauseActionIcon(),
            ACTION_PAUSE_MEDIA,
            buildContentIntent(ACTION_PAUSE)
        )
    }

    @SuppressLint("RestrictedApi")
    private fun registerMediaSession() {
        MediaSessionCompat(this, APP_NAME).apply {
            setCallback(object : MediaSessionCompat.Callback() {
                override fun onPlay() = handleMediaAction(ACTION_PLAY)
                override fun onPause() = handleMediaAction(ACTION_PAUSE)
            })
            isActive = true
        }
    }


    /**
     * 更新通知栏状态
     */
    protected fun updateNotification() {
        startForeground(notificationId, buildBaseNotification().build())
    }

    /**
     * 构建内容意图
     * @param action 附加动作,默认为[ACTION_DEFAULT]
     */
    private fun buildContentIntent(action: String = ACTION_DEFAULT): PendingIntent {
        val contentIntent = Intent(this, getActivityClass()).apply {
            if (action == ACTION_DEFAULT) {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            } else {
                this.action = action
            }
        }

        return PendingIntent.getActivity(
            this,
            0,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun onBind(intent: Intent?) = null
}