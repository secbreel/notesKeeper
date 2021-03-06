package com.secbreel.notes.ui.create_category

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import com.secbreel.notes.model.Category
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.usecases.AddCategoryUseCase
import com.secbreel.notes.usecases.SavePictureUseCase
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.util.*

class CreateCategoryViewModel(
    private val addCategory: AddCategoryUseCase ,
    private val savePicture: SavePictureUseCase
) : ViewModel() {

    private var currentPhotoPath : String = ""

    fun addCategory(title: String): Completable {
        return addCategory(title, currentPhotoPath).subscribeOn(Schedulers.io())
    }

    fun saveImage(uri: Uri) {
        currentPhotoPath = savePicture(uri)
    }
}