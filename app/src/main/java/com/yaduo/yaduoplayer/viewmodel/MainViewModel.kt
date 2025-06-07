package com.yaduo.yaduoplayer.viewmodel

import androidx.lifecycle.viewModelScope
import com.yaduo.common.log.LogUtil
import com.yaduo.yaduoplayer.request.RequestManager
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * MainActivity 配置ViewModel
 *
 * @author YaDuo
 * @since 2025-06-03 18:18:04
 */
class MainViewModel : BaseViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    /** 网络请求管理器实例 **/
    private val request by lazy { RequestManager }

    /**
     * 获取媒体资源
     */
    fun getMediaSource() {
        viewModelScope.launch {
            request.getMediaSource()
        }
    }

    /**
     * 获取精选区分类
     */
    fun getSelectedCategories() {
        viewModelScope.launch {
            request.getSelectedCategories()
                .catch {
                    LogUtil.e(TAG, "error message = ${it.message}")
                }
                .collect { response ->
                    LogUtil.i(TAG, "$response")
                }
        }
    }
}