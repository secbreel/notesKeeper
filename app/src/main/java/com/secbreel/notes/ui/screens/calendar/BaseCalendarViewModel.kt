package com.secbreel.notes.ui.screens.calendar

import android.os.Bundle
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable

abstract class BaseCalendarViewModel : ViewModel() {
    interface Input {
        val onNoteClicked : Observable<Bundle>
    }

    abstract fun attach(input: Input) : Observable<*>
}