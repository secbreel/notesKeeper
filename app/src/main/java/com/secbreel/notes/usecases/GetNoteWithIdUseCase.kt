package com.secbreel.notes.usecases

import com.secbreel.notes.model.Note
import com.secbreel.notes.model.NoteWithCategoryName
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.Observable

class GetNoteWithIdUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(noteId : Int) : Observable<NoteWithCategoryName> {
        return categoryRepository.getNoteWithCategoryName(noteId)
    }
}