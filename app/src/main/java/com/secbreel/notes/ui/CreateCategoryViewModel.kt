package com.secbreel.notes.ui

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
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.util.*

class CreateCategoryViewModel(private val repository: CategoryRepository, private val app : Application) : ViewModel(){

    private lateinit var currentPhotoPath: String

    fun addCategory(title : String) : Completable {
        return repository.insert(Category(title, 0, currentPhotoPath))
            .subscribeOn(Schedulers.io())
    }


    private fun getBitmap(uri : Uri) : Bitmap {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(app.contentResolver, uri))
        }
        return MediaStore.Images.Media.getBitmap(app.contentResolver, uri)
    }

    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir = app.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    fun saveImage(uri: Uri) {
        val thumbnail = getBitmap(uri)
        val file = createImageFile()
        val fos = FileOutputStream(file)
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, fos)
        fos.flush()
        fos.close()
    }
}