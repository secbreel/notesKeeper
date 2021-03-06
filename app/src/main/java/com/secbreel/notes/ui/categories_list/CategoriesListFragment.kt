package com.secbreel.notes.ui.categories_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentCategoriesListBinding
import com.secbreel.notes.databinding.ItemCategoryBinding
import com.secbreel.notes.ui.GlideApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesListFragment() : androidx.fragment.app.Fragment() {
    private val viewModel by viewModel<CategoriesListViewModel>()
    private lateinit var adapter: CategoriesAdapter
    var disposable: Disposable = Disposables.disposed()
    private lateinit var navigationController: NavController
    private lateinit var viewBinding: FragmentCategoriesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCategoriesListBinding.inflate(layoutInflater, container, false)
        val rootView = viewBinding.root
        val categoriesGrid = viewBinding.categoriesGrid

        viewBinding.addCategoryButton.setOnClickListener {
            navigationController.navigate(R.id.action_categoriesListFragment2_to_createCategoryActivity)
        }
        adapter = CategoriesAdapter { view, category ->
            val categoryItemViewBinding = ItemCategoryBinding.bind(view)
            GlideApp.with(this)
                .load(category.imagePath)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(categoryItemViewBinding.categoryBackground)
            categoryItemViewBinding.categoryItem.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("arg1", category.id!!)
                bundle.putString("arg2", category.title)
                navigationController.navigate(
                    R.id.action_categoriesListFragment2_to_categoryScreenFragment3,
                    bundle
                )

            }
            categoryItemViewBinding.categoryTitle.text = category.title
            categoryItemViewBinding.notesCount.text = "${category.notesCount}"
        }
        categoriesGrid.adapter = adapter
        return rootView
    }

    override fun onPause() {
        super.onPause()
        disposable.dispose()
    }

    override fun onResume() {
        super.onResume()
        navigationController =
            Navigation.findNavController(activity as AppCompatActivity, R.id.nav_host_fragment)
        disposable = viewModel.categories
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(adapter::submitList)
            .subscribe()
    }
}