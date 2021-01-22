package com.secbreel.notes.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.secbreel.notes.R
import com.secbreel.notes.model.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesListFragment() : Fragment() {
    private val viewModel by viewModel<CategoriesListViewModel>()
    private lateinit var adapter: CategoriesAdapter
    var disposable: Disposable = Disposables.disposed()
    private lateinit var navigationController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_categories_list, null)
        val categoriesGrid = rootView.findViewById<GridView>(R.id.categoriesGrid)
        navigationController = Navigation.findNavController(activity as AppCompatActivity, R.id.nav_host_fragment)
        rootView.findViewById<Button>(R.id.addCategoryButton).setOnClickListener {
            navigationController.navigate(R.id.action_categoriesListFragment2_to_createCategoryActivity)
        }
        adapter = CategoriesAdapter { view, category ->
            Glide
                .with(this)
                .load(category.imagePath)
                .placeholder(R.drawable.test_background)
                .into(view.findViewById(R.id.categoryBackground))
            view.findViewById<CardView>(R.id.categoryItem).setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("arg1", category.id!!)
                bundle.putString("arg2", category.title)
                navigationController.navigate(R.id.action_categoriesListFragment2_to_categoryScreenFragment3, bundle)

            }
            view.findViewById<TextView>(R.id.categoryTitle).text = category.title
            view.findViewById<TextView>(R.id.notesCount).text = "${category.notesCount}"
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
        setUpToolBar()
        disposable = viewModel.categories
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(adapter::submitList)
            .subscribe()
    }

    private fun setUpToolBar() {
        val toolBar = (activity as AppCompatActivity?)!!.supportActionBar
        toolBar?.title = "Categories"
        toolBar?.setDisplayHomeAsUpEnabled(false)
    }
}