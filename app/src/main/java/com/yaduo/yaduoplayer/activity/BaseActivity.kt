package com.yaduo.yaduoplayer.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.viewbinding.ViewBinding

/**
 * @author YaDuo
 * @since 2025-07-15 17:37:46
 */
abstract class BaseActivity<VB : ViewBinding> : ComponentActivity() {

    protected lateinit var vb: VB

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        enableEdgeToEdge()
        vb = loadVB()
        setContentView(vb.root)
    }

    abstract fun loadVB(): VB
}