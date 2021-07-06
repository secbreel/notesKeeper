package com.secbreel.notes.usecases

import android.app.Application
import android.os.Environment
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.rxjava3.schedulers.Schedulers

class ClearDataUseCase(
    private val categoryRepository: CategoryRepository,
    private val notesRepository: NoteRepository,
    private val app: Application
) {
    operator fun invoke() {
        categoryRepositoryClear()
        notesRepositoryClear()
        clearPictures()
    }

    private fun categoryRepositoryClear() {
        categoryRepository.clear().subscribeOn(Schedulers.io()).subscribe()
    }

    private fun notesRepositoryClear() {
        notesRepository.clear().subscribeOn(Schedulers.io()).subscribe()
    }

    private fun clearPictures() {
        val storageDir = app.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (storageDir?.isDirectory!!)
            for (picture in storageDir.listFiles()!!) {
                picture.delete()
            }
    }
}