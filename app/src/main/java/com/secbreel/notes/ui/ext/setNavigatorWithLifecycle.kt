package com.secbreel.notes.ui.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator

fun NavigatorHolder.setNavigatorWithLifecycle(
    navigator: AppNavigator,
    lifecycleOwner: LifecycleOwner
) {
    this.setNavigator(navigator)
    this.removeNavigatorWithLifecycle(lifecycleOwner)
}

private fun NavigatorHolder.removeNavigatorWithLifecycle(lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            this@removeNavigatorWithLifecycle.removeNavigator()
        }
    })
}