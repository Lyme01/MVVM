package com.exa.base.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDBActivity<VDB : ViewDataBinding> :BaseActivity() {
    lateinit var ui: VDB
    override fun onInit() {
        ui = DataBindingUtil.setContentView(this, mLayoutId)
        ui.lifecycleOwner = this
        super.onInit()
    }

    override fun onDestroy() {
         ui.unbind()
        super.onDestroy()
    }
}