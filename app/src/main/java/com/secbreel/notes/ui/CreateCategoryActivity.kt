package com.secbreel.notes.ui

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.secbreel.notes.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateCategoryActivity : AppCompatActivity() {
    val PICK_IMAGE = 1000
    lateinit var backgroundIconView: ImageView
    private val viewModel by viewModel<CreateCategoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        backgroundIconView = findViewById(R.id.categoryIcon)

        GlideApp
            .with(this)
            .load("")
            .error(R.drawable.ic_baseline_image_24)
            .centerCrop()
            .into(backgroundIconView)

        backgroundIconView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }


        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.addCategory(findViewById<EditText>(R.id.editCategoryTitle).text.toString())
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
                .doOnNext { bitmapUri -> viewModel.saveImage(bitmapUri!!) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { bitmap ->
                    GlideApp
                        .with(this)
                        .load(bitmap)
                        .centerCrop()
                        .into(backgroundIconView)
                }
                .subscribe()
        }
    }
}
