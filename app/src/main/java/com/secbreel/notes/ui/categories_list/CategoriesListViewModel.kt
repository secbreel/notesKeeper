package com.secbreel.notes.ui.categories_list

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.Category
import com.secbreel.notes.model.CategoryWithNotes
import com.secbreel.notes.usecases.GetCategoriesUseCase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CategoriesListViewModel(private val getCategoriesUseCase : GetCategoriesUseCase) : ViewModel() {
    fun getCategories() : Observable<List<CategoryWithNotes>> {
        return getCategoriesUseCase().subscribeOn(Schedulers.io())
    }
}