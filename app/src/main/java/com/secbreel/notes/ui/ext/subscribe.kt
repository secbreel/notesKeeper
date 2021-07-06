package com.secbreel.notes.ui.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

fun <T> Observable<T>.subscribe(
    lifecycleOwner: LifecycleOwner,
    onNext: (T) -> Unit = {},
    onError: (Throwable) -> Unit = { /*Timber.tag(javaClass.name).e(it.stackTraceToString())*/ }
    ) {
        subscribe(onNext, onError).disposeOnDestroy(lifecycleOwner)
    }

    private fun Disposable.disposeOnDestroy(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                this@disposeOnDestroy.dispose()
            }
        })
    }