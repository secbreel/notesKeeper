package com.secbreel.notes.usecases

import com.secbreel.notes.model.CategoryWithNotes
import com.secbreel.notes.model.Note
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.Observable

class GetCategoryWithNotesWithCategoryIdUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(categoryId : Int) : Observable<CategoryWithNotes> {
        return categoryRepository.getCategoryWithNotesWithId(categoryId)
    }

}