package com.secbreel.notes.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.secbreel.notes.R
import com.secbreel.notes.model.Category
import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import javax.security.auth.Subject
import kotlin.properties.Delegates.observable


class CreateCategoryActivity : AppCompatActivity() {
    val PICK_IMAGE = 1000
    lateinit var backgroundIconView: ImageView
    lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        backgroundIconView = findViewById(R.id.categoryIcon)

        backgroundIconView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }

        val categoryRepository = CategoryRepository(this)
        findViewById<Button>(R.id.button).setOnClickListener {
            val category =
                Category(
                    findViewById<EditText>(R.id.editCategoryTitle).text.toString(),
                    0,
                    currentPhotoPath
                )
            categoryRepository.insert(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { finish() }
                .subscribe()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Observable.just(data?.data)
                .subscribeOn(Schedulers.io())
                .doOnNext {bitmapUri -> saveImage(getBitmap(bitmapUri!!), createImageFile()) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { bitmap ->
                    Glide
                        .with(this)
                        .load(bitmap)
                        .placeholder(R.drawable.test_background)
                        .centerCrop()
                        .into(backgroundIconView)
                }
                .subscribe()
        }
    }


    private fun saveImage(thumbnail: Bitmap, file: File) {
        val fos = FileOutputStream(file)
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, fos)
        fos.flush()
        fos.close()
    }


    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun getBitmap(uri : Uri) : Bitmap {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return ImageDecoder.decodeBitmap(
            ImageDecoder.createSource(this.contentResolver, uri))
        }
        return MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
    }
}
