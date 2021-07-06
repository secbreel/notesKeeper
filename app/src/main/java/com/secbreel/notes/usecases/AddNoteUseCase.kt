package com.secbreel.notes.usecases

import com.secbreel.notes.model.Note
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.rxjava3.core.Completable
import java.text.SimpleDateFormat
import java.util.*


class AddNoteUseCase(
    private val noteRepository: NoteRepository,
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(title: String, text: String, categoryId: Int) : Completable {
        return noteRepository.insert(Note(title, text, getDate(), categoryId))
            .andThen (
                noteRepository.observeWithCategoryId(categoryId)
                    .firstOrError()
                    .flatMapCompletable { list ->
                        categoryRepository.updateNotesCount(categoryId, list.size)
                    }
            )
    }

    private fun getDate(): String {
        return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
    }
}