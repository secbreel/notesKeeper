package com.secbreel.notes.usecases

import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class NotesRepositoryClearUseCase(private val notesRepository : NoteRepository) {
    operator fun invoke() {
        notesRepository.clear().subscribeOn(Schedulers.io()).subscribe()
    }
}