package com.secbreel.notes.ui.create_category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentCreateCategoryBinding
import com.secbreel.notes.ui.GlideApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateCategoryFragment : Fragment(R.layout.fragment_create_category) {

    lateinit var backgroundIconView: ImageView
    private val viewModel by viewModel<CreateCategoryViewModel>()
    private val viewBinding by viewBinding(FragmentCreateCategoryBinding::bind)
    private val router by inject<Router>()

    private val getImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                Observable.just(result.data?.data)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Create category"

        backgroundIconView = viewBinding.categoryIcon

        GlideApp.with(this)
            .load("")
            .error(R.drawable.ic_baseline_image_search_24)
            .centerCrop()
            .into(backgroundIconView)

        backgroundIconView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            getImage.launch(intent)
        }


        viewBinding.button.setOnClickListener {
            viewModel.addCategory(viewBinding.editCategoryTitle.text.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { router.exit() }
                .subscribe()
        }
    }
}