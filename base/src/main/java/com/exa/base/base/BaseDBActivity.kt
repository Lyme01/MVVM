package com.exa.base.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.exa.base.R
import com.gyf.immersionbar.ImmersionBar

abstract class BaseDBActivity<VDB : ViewDataBinding> :BaseActivity() {
    lateinit var ui: VDB
    lateinit var context: Context
    var mIntent: Intent? = null
    open var isDark = false
    protected var mBundle: Bundle? = null
    override fun onInit() {
        ui = DataBindingUtil.setContentView(this, mLayoutId)
        ui.lifecycleOwner = this
        initStatusBar()
        super.onInit()
    }

    protected fun initStatusBar(){
        ImmersionBar.with(this)
            .statusBarDarkFont(isDark, 0.2f)
            .navigationBarColor(R.color.main_color)
            .init()
        context = this
        mIntent = intent
    }

    override fun onDestroy() {
         ui.unbind()
        super.onDestroy()
    }
}