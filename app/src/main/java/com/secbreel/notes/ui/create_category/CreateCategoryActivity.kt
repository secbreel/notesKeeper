package com.secbreel.notes.ui.create_category

import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.secbreel.notes.R
import com.secbreel.notes.ui.GlideApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateCategoryActivity : AppCompatActivity() {
    lateinit var backgroundIconView: ImageView
    private val viewModel by viewModel<CreateCategoryViewModel>()

    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        Observable.just(uri)
            .subscribeOn(Schedulers.io())
            .doOnNext { bitmapUri -> viewModel.saveImage(bitmapUri!!) }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { bitmap ->
                GlideApp.with(this)
                    .load(bitmap)
                    .placeholder(R.drawable.ic_baseline_image_search_24)
                    .centerCrop()
                    .into(backgroundIconView)
            }
            .subscribe()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        setSupportActionBar(findViewById(R.id.mainToolBar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "create category"

        backgroundIconView = findViewById(R.id.categoryIcon)

        GlideApp.with(this)
            .load("")
            .error(R.drawable.ic_baseline_image_search_24)
            .centerCrop()
            .into(backgroundIconView)

        backgroundIconView.setOnClickListener {
            getImage.launch("image/")
        }


        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.addCategory(findViewById<EditText>(R.id.editCategoryTitle).text.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { finish() }
                .subscribe()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
