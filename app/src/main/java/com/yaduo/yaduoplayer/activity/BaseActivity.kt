package com.yaduo.yaduoplayer.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.viewbinding.ViewBinding

/**
 * @author YaDuo
 * @since 2025-07-15 17:37:46
 */
abstract class BaseActivity<VB : ViewBinding> : ComponentActivity() {

    protected lateinit var vb: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        vb = loadVB()
        setContentView(vb.root)
        initViews()
    }

    /**
     * 加载ViewBinding
     */
    abstract fun loadVB(): VB

    /**
     * 初始化控件
     */
    abstract fun initViews()
}