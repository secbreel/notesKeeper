package com.secbreel.notes.usecases

import com.secbreel.notes.model.CategoryWithNotes
import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.rxjava3.core.Observable

class GetCategoryWithNotesWithCategoryIdUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(categoryId : Int) : Observable<CategoryWithNotes> {
        return categoryRepository.getCategoryWithNotesWithId(categoryId)
    }

}