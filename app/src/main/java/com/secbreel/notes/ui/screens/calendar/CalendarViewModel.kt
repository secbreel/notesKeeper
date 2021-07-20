package com.secbreel.notes.ui.screens.calendar

import com.secbreel.notes.model.ListItem
import com.secbreel.notes.ui.screens.initial.IInitialRouter
import com.secbreel.notes.usecases.GetAllNotesUseCase
import com.secbreel.notes.usecases.GetGroupedNotesUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CalendarViewModel(
    private val getAllNotes: GetAllNotesUseCase,
    private val getGroupedNotes: GetGroupedNotesUseCase,
    private val router: IInitialRouter
) : BaseCalendarViewModel() {

    fun getNotes(): Observable<List<ListItem>> =
        getAllNotes()
            .map { notes ->
                getGroupedNotes.invoke(notes)
            }.subscribeOn(Schedulers.io())

    override fun attach(input: Input): Observable<*> {
        return with(input) {
            onNoteClicked
                .throttleFirst(10, TimeUnit.MILLISECONDS)
                .doOnNext { router.navigateNoteScreen(it) }
        }
    }

}