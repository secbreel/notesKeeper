package com.secbreel.notes.ui.create_note

import androidx.lifecycle.ViewModel
import com.secbreel.notes.usecases.AddNoteUseCase
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class CreateNotesViewModel(private val addNote: AddNoteUseCase) : ViewModel() {


    fun saveNote(title: String, text: String, categoryId: Int) : Completable {
        return addNote(title, text, categoryId).subscribeOn(Schedulers.io())
    }

}