package com.secbreel.notes.ui.category_screen

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.ListItem
import com.secbreel.notes.usecases.GetGroupedNotesUseCase
import com.secbreel.notes.usecases.GetNotesWithCategoryIdUseCase
import io.reactivex.schedulers.Schedulers

class CategoryScreenViewModel(
    private val getNotesWithCategoryId: GetNotesWithCategoryIdUseCase,
    private val getGroupedNotes: GetGroupedNotesUseCase
) : ViewModel() {

    fun getNotes(categoryId: Int): io.reactivex.Observable<List<ListItem>> =
        getNotesWithCategoryId(categoryId)
            .map { notes ->
                getGroupedNotes.invoke(notes)
            }.subscribeOn(Schedulers.io())
}