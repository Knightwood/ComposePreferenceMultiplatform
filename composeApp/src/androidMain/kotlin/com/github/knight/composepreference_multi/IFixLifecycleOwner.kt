package com.github.knight.composepreference_multi

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class ExampleActivity : Activity(), IFixLifecycleOwner by FixLifecycleOwner() {
    init {
        this.register(this)
    }
}

interface IFixLifecycleOwner : LifecycleOwner

inline fun <reified T : Activity> IFixLifecycleOwner.register(activity: T) {
    (this as? FixLifecycleOwner)?.register(activity)
}

/**
 * 给不支持生命周期的activity注册生命周期
 * 原理是监听activity的生命周期，然后调用LifecycleRegistry的handleLifecycleEvent方法
 */
class FixLifecycleOwner : IFixLifecycleOwner {
    val lifecycleRegistry = LifecycleRegistry(this)
    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    private fun handleLifecycleEvent(event: Lifecycle.Event) {
        lifecycleRegistry.handleLifecycleEvent(event)
    }

    val observer: Application.ActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        }

        override fun onActivityStarted(activity: Activity) {
            handleLifecycleEvent(Lifecycle.Event.ON_START)
        }

        override fun onActivityResumed(activity: Activity) {
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        override fun onActivityPaused(activity: Activity) {
            handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        }

        override fun onActivityStopped(activity: Activity) {
            handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        }

        override fun onActivityDestroyed(activity: Activity) {
            handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            activity.application.unregisterActivityLifecycleCallbacks(observer)
        }
    }

    inline fun <reified T : Activity> register(activity: T) {
        // 如果activity是ComponentActivity的子类，则不能使用
        if (ComponentActivity::class.java.isAssignableFrom(T::class.java)) {
            throw IllegalArgumentException("ComponentActivity is not supported")
        }
        activity.application.registerActivityLifecycleCallbacks(observer)
    }
}
