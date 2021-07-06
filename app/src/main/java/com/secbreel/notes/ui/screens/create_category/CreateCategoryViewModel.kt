package com.secbreel.notes.ui.screens.create_category

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.secbreel.notes.usecases.AddCategoryUseCase
import com.secbreel.notes.usecases.SavePictureUseCase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class CreateCategoryViewModel(
    private val addCategory: AddCategoryUseCase,
    private val savePicture: SavePictureUseCase
) : ViewModel() {

    private var currentPhotoPath: String = ""

    fun addCategory(title: String): Completable {
        return addCategory(title, currentPhotoPath).subscribeOn(Schedulers.io())
    }

    fun saveImage(uri: Uri) {
        currentPhotoPath = savePicture(uri)
    }
}