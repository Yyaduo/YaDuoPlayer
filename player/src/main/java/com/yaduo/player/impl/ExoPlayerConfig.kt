package com.yaduo.player.impl

import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.LoadControl
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.exoplayer.trackselection.TrackSelector
import com.yaduo.common.applogic.AppLogicUtil

/**
 * @author YaDuo
 * @since 2025-05-22 17:01:48
 */
@UnstableApi
data class ExoPlayerConfig(
    val loadControl: LoadControl = DefaultLoadControl(),
    val trackSelector: TrackSelector = DefaultTrackSelector(AppLogicUtil.getApp())
)
