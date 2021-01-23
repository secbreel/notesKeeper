package com.secbreel.notes.ui

import androidx.lifecycle.ViewModel
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class SettingsFragmentViewModel(private val categoryRepository: CategoryRepository, notesRepository: NoteRepository) : ViewModel() {
    val categoryRepositoryClear : Completable = categoryRepository.clear().subscribeOn(Schedulers.io())
    val notesRepositoryClear : Completable = notesRepository.clear().subscribeOn(Schedulers.io())
}