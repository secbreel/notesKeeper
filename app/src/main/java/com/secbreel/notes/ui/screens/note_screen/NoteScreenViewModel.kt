package com.secbreel.notes.ui.screens.note_screen

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.NoteWithCategoryName
import com.secbreel.notes.usecases.GetNoteWithIdUseCase
import com.secbreel.notes.usecases.UpdateNoteUseCase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class NoteScreenViewModel(private val getNoteWithId : GetNoteWithIdUseCase, private val updateNoteUseCase: UpdateNoteUseCase) : ViewModel() {
    fun getNote(noteId : Int) : Observable<NoteWithCategoryName> {
        return getNoteWithId(noteId).subscribeOn(Schedulers.io())
    }

    fun updateNote(noteId: Int, title: String, text: String) : Completable {
        return updateNoteUseCase(noteId, title, text).subscribeOn(Schedulers.io())
    }

}