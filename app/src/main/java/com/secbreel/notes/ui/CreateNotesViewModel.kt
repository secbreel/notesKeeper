package com.secbreel.notes.ui

import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.Note
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class CreateNotesViewModel(private val repository: NoteRepository) : ViewModel() {
    fun saveNote(title: String, text: String, categoryId: Int): Completable {
        return repository.insert(Note(title, text, getDate(), categoryId))
            .subscribeOn(Schedulers.io())
    }

    private fun getDate(): String {
        return SimpleDateFormat("dd.MM.YYYY", Locale.getDefault()).format(Date())
    }

}