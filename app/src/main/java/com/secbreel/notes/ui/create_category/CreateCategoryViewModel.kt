package com.secbreel.notes.ui.create_category

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.secbreel.notes.usecases.AddCategoryUseCase
import com.secbreel.notes.usecases.SavePictureUseCase
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

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