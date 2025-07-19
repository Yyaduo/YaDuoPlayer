package com.yaduo.yaduoplayer

import android.os.Bundle
import androidx.media3.common.util.UnstableApi
import com.yaduo.common.Utils
import com.yaduo.common.log.LogUtil
import com.yaduo.yaduoplayer.activity.BaseActivity
import com.yaduo.yaduoplayer.databinding.LayoutActivityMainBinding
import com.yaduo.yaduoplayer.service.MediaPlayerService
import com.yaduo.yaduoplayer.viewmodel.MainViewModel

/**
 * ### Activity - 主页
 * @author YaDuo
 * @since 2025-07-16 18:04:10
 */
@UnstableApi
class MainActivity : BaseActivity<LayoutActivityMainBinding>() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun loadVB() = LayoutActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runOnUiThread { Utils.registerLifecycleEventObserver() }
        MediaPlayerService.launchMediaPlayerService(this)
        LogUtil.i(TAG, "${MainViewModel().getSelectedCategories()}")
    }

    override fun onResume() {
        super.onResume()
        LogUtil.i(
            TAG,
            "onResume: app launch time = ${System.currentTimeMillis() - YaduoPlayerApplication.getStartAppTime()}"
        )
    }
}