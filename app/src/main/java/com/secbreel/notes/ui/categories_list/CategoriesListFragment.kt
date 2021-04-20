package com.secbreel.notes.ui.categories_list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentCategoriesListBinding
import com.secbreel.notes.databinding.ItemCategoryBinding
import com.secbreel.notes.ui.GlideApp
import com.secbreel.notes.ui.category_screen.CategoryScreenFragment
import com.secbreel.notes.ui.create_category.CreateCategoryFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesListFragment : Fragment(R.layout.fragment_categories_list) {
    //TODO Заменить на cicerone
    //TODO Убрать disposable с экрана *extension lifeCycle observer generic*
    //TODO убрать навигацию на viewModel
    private val viewModel by viewModel<CategoriesListViewModel>()
    private lateinit var adapter: CategoriesAdapter
    var disposable: Disposable = Disposables.disposed()
    private val viewBinding by viewBinding(FragmentCategoriesListBinding::bind)
    private val router by inject<Router>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Categories"
        viewBinding.addCategoryButton.setOnClickListener {
            router.navigateTo(FragmentScreen {
                CreateCategoryFragment()
            })
        }
        adapter = CategoriesAdapter { view, categoryWithNotes ->
            val categoryItemViewBinding = ItemCategoryBinding.bind(view)
            GlideApp.with(this)
                .load(categoryWithNotes.category.imagePath)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(categoryItemViewBinding.categoryBackground)
            categoryItemViewBinding.categoryItem.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("arg1", categoryWithNotes.category.id!!)
                bundle.putString("arg2", categoryWithNotes.category.title)
                router.navigateTo(FragmentScreen {
                    CategoryScreenFragment().apply {
                        arguments = bundle
                    }
                })

            }
            categoryItemViewBinding.categoryTitle.text = categoryWithNotes.category.title
            categoryItemViewBinding.notesCount.text = "${categoryWithNotes.category.notesCount}"
        }
        viewBinding.categoriesGrid.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        disposable.dispose()
    }

    override fun onResume() {
        super.onResume()
        disposable = viewModel.getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(adapter::submitList)
            .subscribe()
    }
}