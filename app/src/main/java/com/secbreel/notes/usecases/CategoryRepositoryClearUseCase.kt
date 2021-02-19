package com.secbreel.notes.usecases

import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class CategoryRepositoryClearUseCase(private val categoryRepository : CategoryRepository) {
    operator fun invoke() {
        categoryRepository.clear().subscribeOn(Schedulers.io()).subscribe()
    }
}