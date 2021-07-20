package com.secbreel.notes.ui.screens

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable

abstract class BaseApplicationActivityViewModel : ViewModel() {

    interface Input {
        val onReturnClicked : Observable<Unit>
    }

    abstract fun attach(input: Input) : Observable<*>

}