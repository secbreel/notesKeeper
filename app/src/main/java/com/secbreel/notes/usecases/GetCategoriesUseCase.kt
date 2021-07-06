package com.secbreel.notes.usecases

import com.secbreel.notes.persistance.CategoryRepository


class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke() = categoryRepository.observeAll()
}