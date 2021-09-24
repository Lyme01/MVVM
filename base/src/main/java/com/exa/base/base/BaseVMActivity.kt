package com.exa.base.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMActivity<VDB : ViewDataBinding, VM : BaseVM> : BaseDBActivity<VDB>() {
  //=========================  =================================

  lateinit var vm: VM
  //=========================  =================================
  //=========================  =================================

  //=========================init  =================================
  fun createVM(modelClass: Class<VM>): VM {
//    vm = ViewModelProviders.of(this).get(modelClass)
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



  override fun onDestroy() {
    onDestroyVM()

    super.onDestroy()
  }
}