package com.secbreel.notes.usecases

import com.secbreel.notes.model.Note
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.Observable

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke() : Observable<List<Note>> {
        return noteRepository.observeAll()
    }
}