package com.secbreel.notes.ui.ext

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

fun FragmentActivity.onCurrentFragmentChanged(block: Fragment.() -> Unit) {
    val listener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentResumed(fm: FragmentManager, fragment: Fragment) {
            super.onFragmentResumed(fm, fragment)
            if (fragment !is DialogFragment)
                fragment.block()
        }

    }

    lifecycle.addObserver(object : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            supportFragmentManager.registerFragmentLifecycleCallbacks(listener, false)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            supportFragmentManager.unregisterFragmentLifecycleCallbacks(listener)
        }
    })
}

val FragmentActivity.currentlyVisibleFragment: Fragment? get() = supportFragmentManager.fragments.lastOrNull()