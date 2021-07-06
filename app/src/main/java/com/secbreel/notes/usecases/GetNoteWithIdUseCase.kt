package com.secbreel.notes.usecases

import com.secbreel.notes.model.NoteWithCategoryName
import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.rxjava3.core.Observable

class GetNoteWithIdUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(noteId : Int) : Observable<NoteWithCategoryName> {
        return categoryRepository.getNoteWithCategoryName(noteId)
    }
}