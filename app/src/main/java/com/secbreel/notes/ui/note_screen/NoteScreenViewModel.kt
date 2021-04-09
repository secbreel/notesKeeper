package com.secbreel.notes.ui.note_screen

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.Note
import com.secbreel.notes.model.NoteWithCategoryName
import com.secbreel.notes.usecases.GetNoteWithIdUseCase
import com.secbreel.notes.usecases.UpdateNoteUseCase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NoteScreenViewModel(private val getNoteWithId : GetNoteWithIdUseCase, private val updateNoteUseCase: UpdateNoteUseCase) : ViewModel() {
    fun getNote(noteId : Int) : Observable<NoteWithCategoryName> {
        return getNoteWithId(noteId).subscribeOn(Schedulers.io())
    }

    fun updateNote(noteId: Int, title: String, text: String) : Completable {
        return updateNoteUseCase(noteId, title, text).subscribeOn(Schedulers.io())
    }

}