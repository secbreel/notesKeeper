package com.secbreel.notes.usecases

import com.secbreel.notes.model.Note
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.Observable

class GetNoteWithIdUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(noteId : Int) : Observable<Note> {
        return noteRepository.getNoteWithId(noteId)
    }
}