package com.secbreel.notes.usecases

import com.secbreel.notes.model.Category
import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.Completable

class AddCategoryUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(title : String, currentPhotoPath : String) : Completable {
        return categoryRepository.insert(Category(title, 0, currentPhotoPath))
    }
}