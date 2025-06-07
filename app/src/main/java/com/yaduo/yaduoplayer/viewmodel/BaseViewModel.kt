package com.yaduo.yaduoplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ### ViewModel基类
 *  - 常用状态管理
 *  - 协程扩展
 *  - 错误处理
 *
 * @author YaDuo
 * @since 2025-06-03 18:17:27
 */
open class BaseViewModel : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState


    /**
     * 加载状态
     */
    sealed class LoadingState {
        object Loading : LoadingState()
        object Idle : LoadingState()
    }
}