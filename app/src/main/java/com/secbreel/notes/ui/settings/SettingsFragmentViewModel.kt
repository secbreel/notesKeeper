package com.secbreel.notes.ui.settings

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Environment
import android.preference.PreferenceManager
import android.preference.PreferenceManager.getDefaultSharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class SettingsFragmentViewModel(
    categoryRepository: CategoryRepository,
    notesRepository: NoteRepository,
    private val app: Application
) : ViewModel() {
    val categoryRepositoryClear: Completable =
        categoryRepository.clear().subscribeOn(Schedulers.io()).doOnComplete { deletePictures() }
    val notesRepositoryClear: Completable = notesRepository.clear().subscribeOn(Schedulers.io())

    private fun deletePictures() {
        val storageDir = app.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (storageDir?.isDirectory!!)
            for (picture in storageDir.listFiles()!!) {
                picture.delete()
            }
    }

    fun saveThemeMode(theme : Int) {
        app.getSharedPreferences("preferences", MODE_PRIVATE).edit().putInt("theme", theme).apply()
    }
}