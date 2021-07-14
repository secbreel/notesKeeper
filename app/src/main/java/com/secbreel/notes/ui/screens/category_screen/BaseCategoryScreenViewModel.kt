package com.secbreel.notes.ui.screens.category_screen

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.ListItem
import io.reactivex.rxjava3.core.Observable

abstract class BaseCategoryScreenViewModel : ViewModel() {

    abstract fun getNotes(id: Int): Observable<List<ListItem>>

    abstract fun attach(input: Input): Observable<*>

    interface Input {
        val onNoteClicked: Observable<Bundle>
        val onCreateNoteClicked: Observable<Bundle>
    }
}