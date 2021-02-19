package com.secbreel.notes.ui.categories_list

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.Category
import com.secbreel.notes.usecases.GetCategoriesUseCase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CategoriesListViewModel(private val getCategories : GetCategoriesUseCase) : ViewModel() {

    val categories : Observable<List<Category>> = getCategories().subscribeOn(Schedulers.io())


}