package com.exa.base.base

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.alibaba.android.arouter.launcher.ARouter

class BaseApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())
        ARouter.init(this)
    }

    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    private inner class ApplicationLifecycleObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        private fun onAppForeground() {
            // TODO: "ApplicationObserver: app moved to foreground"
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        private fun onAppBackground() {
            // TODO: "ApplicationObserver: app moved to background"
        }
    }
}