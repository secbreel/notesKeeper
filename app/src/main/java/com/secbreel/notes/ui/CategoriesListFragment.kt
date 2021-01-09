package com.secbreel.notes.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.secbreel.notes.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesListFragment : Fragment() {
    private val viewModel by viewModel<CategoriesListViewModel>()
    private lateinit var adapter : CategoriesAdapter
    var disposable : Disposable = Disposables.disposed()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_categories_list, null)
        val categoriesGrid = rootView.findViewById<GridView>(R.id.categoriesGrid)
        rootView.findViewById<Button>(R.id.addCategoryButton).setOnClickListener {
            startActivity(Intent(activity, CreateCategoryActivity::class.java))
        }
        adapter = CategoriesAdapter { view, category ->
            Glide
                .with(this)
                .load(category.imagePath)
                .placeholder(R.drawable.test_background)
                .into(view.findViewById(R.id.categoryBackground))
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
        disposable = viewModel.categories
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(adapter::submitList)
            .subscribe()
    }
}