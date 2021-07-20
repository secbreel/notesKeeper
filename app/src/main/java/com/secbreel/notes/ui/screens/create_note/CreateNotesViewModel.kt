package com.secbreel.notes.ui.screens.create_note

import androidx.lifecycle.ViewModel
import com.secbreel.notes.ui.screens.initial.IInitialRouter
import com.secbreel.notes.usecases.AddNoteUseCase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class CreateNotesViewModel(private val addNote: AddNoteUseCase, private val router: IInitialRouter) : ViewModel() {

    fun returnToPreviousScreen() {
        router.navigateToPreviousScreen()
    }


    fun saveNote(title: String, text: String, categoryId: Int) : Completable {
        return addNote(title, text, categoryId).subscribeOn(Schedulers.io())
    }

}