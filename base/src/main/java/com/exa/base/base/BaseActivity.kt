package com.exa.base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity :AppCompatActivity(),ICreate {
    //=========================  =================================
    abstract var mLayoutId: Int
    //=========================  =================================
    val mTag = javaClass.simpleName

    open var isDebug = false
    //=========================  =================================

    override fun onInit() {
        onInitView()
        onInitData()
    }

    override fun onInitView() {
    }

    override fun onInitData() {
    }

    //=========================main ==================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInit()
    }
}