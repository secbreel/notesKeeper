package com.secbreel.notes.ui.calendar

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.ListItem
import com.secbreel.notes.usecases.GetAllNotesUseCase
import com.secbreel.notes.usecases.GetGroupedNotesUseCase
import io.reactivex.schedulers.Schedulers

class CalendarViewModel(
    private val getAllNotes: GetAllNotesUseCase,
    private val getGroupedNotes: GetGroupedNotesUseCase
) : ViewModel() {

    fun getNotes(): io.reactivex.Observable<List<ListItem>> =
        getAllNotes()
            .map { notes ->
                getGroupedNotes.invoke(notes)
            }.subscribeOn(Schedulers.io())

}