package com.secbreel.notes.usecases

import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.Completable
import java.text.SimpleDateFormat
import java.util.*

class UpdateNoteUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(id: Int, title: String, text: String) : Completable {
        return noteRepository.updateNote(id, title, text, getDate())
    }


    private fun getDate(): String {
        return SimpleDateFormat("dd.MM.YYYY", Locale.getDefault()).format(Date())
    }
}