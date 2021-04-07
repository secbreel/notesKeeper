package com.secbreel.notes.ui.category_screen

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.CategoryWithNotes
import com.secbreel.notes.model.ListItem
import com.secbreel.notes.usecases.GetGroupedNotesUseCase
import com.secbreel.notes.usecases.GetCategoryWithNotesWithCategoryIdUseCase
import io.reactivex.schedulers.Schedulers

class CategoryScreenViewModel(
    private val getCategoryWithNotesWithCategoryId: GetCategoryWithNotesWithCategoryIdUseCase,
    private val getGroupedNotes: GetGroupedNotesUseCase
) : ViewModel() {

    fun getNotes(categoryId: Int): io.reactivex.Observable<CategoryWithNotes> =
        getCategoryWithNotesWithCategoryId(categoryId)
            .doOnNext { category ->
                getGroupedNotes.invoke(category.notes)
            }.subscribeOn(Schedulers.io())
}