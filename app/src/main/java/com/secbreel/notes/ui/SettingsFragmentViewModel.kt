package com.secbreel.notes.ui

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.Category
import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class SettingsFragmentViewModel(private val repository: CategoryRepository) : ViewModel() {
    val repositoryClear : Completable = repository.clear().subscribeOn(Schedulers.io())
}