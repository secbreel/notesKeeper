package com.secbreel.notes.ui.create_category

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.secbreel.notes.R
import com.secbreel.notes.databinding.ActivityCreateCategoryBinding
import com.secbreel.notes.ui.GlideApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateCategoryActivity : AppCompatActivity() {
    lateinit var backgroundIconView: ImageView
    private val viewModel by viewModel<CreateCategoryViewModel>()
    private lateinit var viewBinding: ActivityCreateCategoryBinding

    private val getImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
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
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCreateCategoryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolBar.mainToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "create category"

        backgroundIconView = viewBinding.categoryIcon

        GlideApp.with(this)
            .load("")
            .error(R.drawable.ic_baseline_image_search_24)
            .centerCrop()
            .into(backgroundIconView)

        backgroundIconView.setOnClickListener {
            getImage.launch("image/")
        }


        viewBinding.button.setOnClickListener {
            viewModel.addCategory(viewBinding.editCategoryTitle.text.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { finish() }
                .subscribe()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
