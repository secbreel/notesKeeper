package com.secbreel.notes.ui.screens.calendar

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.ListItem
import com.secbreel.notes.usecases.GetAllNotesUseCase
import com.secbreel.notes.usecases.GetGroupedNotesUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class CalendarViewModel(
    private val getAllNotes: GetAllNotesUseCase,
    private val getGroupedNotes: GetGroupedNotesUseCase
) : ViewModel() {

    fun getNotes(): Observable<List<ListItem>> =
        getAllNotes()
            .map { notes ->
                getGroupedNotes.invoke(notes)
            }.subscribeOn(Schedulers.io())

}