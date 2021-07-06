package com.secbreel.notes.ui.screens.initial

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.CategoryWithNotes
import io.reactivex.rxjava3.core.Observable

abstract class BaseCategoriesListViewModel : ViewModel() {

    abstract val categories: Observable<List<CategoryWithNotes>>

    abstract fun attach(input : Input) : Observable<*>

    interface Input {
        val onCategoryClicked : Observable<Bundle>
        val onCreateCategoryClicked : Observable<Unit>
    }
}