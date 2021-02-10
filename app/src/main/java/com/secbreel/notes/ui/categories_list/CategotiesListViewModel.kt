package com.secbreel.notes.ui.categories_list

import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.secbreel.notes.R
import com.secbreel.notes.model.Category
import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class CategoriesListViewModel(private val repository: CategoryRepository) : ViewModel() {

    val categories : Observable<List<Category>> = repository.observeAll()
        .subscribeOn(Schedulers.io())


}