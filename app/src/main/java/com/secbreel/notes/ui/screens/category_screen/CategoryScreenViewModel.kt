package com.secbreel.notes.ui.screens.category_screen

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.ListItem
import com.secbreel.notes.usecases.GetCategoryWithNotesWithCategoryIdUseCase
import com.secbreel.notes.usecases.GetGroupedNotesUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryScreenViewModel(
    private val getCategoryWithNotesWithCategoryId: GetCategoryWithNotesWithCategoryIdUseCase,
    private val getGroupedNotes: GetGroupedNotesUseCase
) : ViewModel() {

    fun getNotes(categoryId: Int): Observable<List<ListItem>>? =
        getCategoryWithNotesWithCategoryId(categoryId)
            .map { category ->
                getGroupedNotes.invoke(category.notes)
            }.subscribeOn(Schedulers.io())

}