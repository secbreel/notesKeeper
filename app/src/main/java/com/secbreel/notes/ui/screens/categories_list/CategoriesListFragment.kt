package com.secbreel.notes.ui.screens.categories_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.view.clicks
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentCategoriesListBinding
import com.secbreel.notes.databinding.ItemCategoryBinding
import com.secbreel.notes.ui.ext.GlideApp
import com.secbreel.notes.ui.ext.subscribe
import com.secbreel.notes.ui.screens.ApplicationActivity.Companion.toolbarTitle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesListFragment : Fragment(R.layout.fragment_categories_list) {
    private val viewModel by viewModel<BaseCategoriesListViewModel>()
    private val viewBinding by viewBinding(FragmentCategoriesListBinding::bind)

    private var onCategoryClicked: PublishSubject<Bundle> = PublishSubject.create()

    private var adapter = CategoriesAdapter { view, categoryWithNotes ->
        val categoryItemViewBinding = ItemCategoryBinding.bind(view)
        GlideApp.with(this)
            .load(categoryWithNotes.category.imagePath)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_not_supported_24)
            .into(categoryItemViewBinding.categoryBackground)

        view.setOnClickListener {
            val bundle = Bundle()
            categoryWithNotes.category.id?.let { bundle.putInt("arg1", it) }
            bundle.toolbarTitle = categoryWithNotes.category.title

            onCategoryClicked.onNext(bundle)
        }

        categoryItemViewBinding.categoryTitle.text = categoryWithNotes.category.title
        categoryItemViewBinding.notesCount.text = "${categoryWithNotes.category.notesCount}"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setup()
        categoriesGrid.adapter = adapter
    }

    private fun BaseCategoriesListViewModel.setup() = with(viewBinding) {
        attach(object : BaseCategoriesListViewModel.Input {
            override val onCreateCategoryClicked = addCategoryButton.clicks()
            override val onCategoryClicked = this@CategoriesListFragment.onCategoryClicked
        }).subscribe(viewLifecycleOwner)

        viewModel.categories
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { adapter.submitList(it) }
            .subscribe(viewLifecycleOwner)
    }
}