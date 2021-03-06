package com.secbreel.notes.usecases

import com.secbreel.notes.model.Note
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.Observable

class GetNotesWithIdUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(categoryId : Int) : Observable<List<Note>> {
        return noteRepository.observeWithId(categoryId)
    }

}