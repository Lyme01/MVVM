package com.exa.base.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

open class BaseVM : ViewModel() {

    //=========================  =================================
    open val mTag = javaClass.simpleName

    open var isDebug = false
    //=========================  =================================
    val rxer by lazy { CompositeDisposable() }

    //=========================  =================================
    open fun sub(obs: DisposableObserver<*>) {
        rxer.add(obs)
    }

    open fun onDestroy() {
        rxer.clear()
    }
    //========================= init  =================================

    //========================= main ==================================

}