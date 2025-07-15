package com.yaduo.player.model

import android.net.Uri
import androidx.core.net.toUri

/**
 * @author YaDuo
 * @since 2025-05-18 02:31:12
 */
data class MediaItemData(
    var id: Int = 0,
    var title: String = "test title",
    var artist: String = "test artist",
    var logo: String = "123",
    var uri: Uri = "https://streaming.radiostreamlive.com/radiorockon_devices?dist=onsss".toUri()
)
