package com.secbreel.notes.usecases

import com.secbreel.notes.model.Category
import com.secbreel.notes.model.CategoryWithNotes
import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.Observable

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke() = categoryRepository.observeAll()
}