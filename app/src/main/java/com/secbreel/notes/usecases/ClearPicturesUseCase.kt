package com.secbreel.notes.usecases

import android.app.Application
import android.os.Environment

class ClearPicturesUseCase(private val app: Application) {
    operator fun invoke() {
        val storageDir = app.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (storageDir?.isDirectory!!)
            for (picture in storageDir.listFiles()!!) {
                picture.delete()
            }
    }
}