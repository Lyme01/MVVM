package com.exa.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMFragment<VDB : ViewDataBinding, VM : BaseVM> : BaseKtFragment() {
    //=========================  =================================
    lateinit var ui: VDB
    lateinit var vm: VM

    //=========================  =================================
    //=========================  =================================

    //=========================init  =================================
    fun createVM(modelClass: Class<VM>): VM {
        vm= ViewModelProvider(this)[modelClass]
        return onCreateVM(vm)
    }

    open fun onCreateVM(vm: VM): VM {

        return vm
    }

    open fun onDestroyVM() {
        if (::vm.isInitialized) {
            vm.onDestroy()
        }
    }

    override fun onCreateRootView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ui = DataBindingUtil.inflate(inflater, mLayoutId, container, false)
        ui.lifecycleOwner = this
        return ui.root
    }

    override fun onDestroy() {
        onDestroyVM()
        super.onDestroy()
    }
}