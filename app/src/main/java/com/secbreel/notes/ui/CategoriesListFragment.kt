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
import com.secbreel.notes.R
import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CategoriesListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_categories_list, null)
        val categoriesGrid = rootView.findViewById<GridView>(R.id.categoriesGrid)
        CategoryRepository(requireContext()).observeAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { categories ->
                categoriesGrid.adapter = CategoriesAdapter(categories) { view, category ->
                    view.findViewById<TextView>(R.id.categoryTitle).text = category.title
                    view.findViewById<TextView>(R.id.notesCount).text = "${category.notesCount}"
                }
            }.subscribe()


        rootView.findViewById<Button>(R.id.addCategoryButton).setOnClickListener {
            /*Toast.makeText(
                    this,
                    "clicked!",
                    Toast.LENGTH_SHORT
            ).show()*/
            startActivity(Intent(activity, CreateCategoryActivity::class.java))
        }
        return rootView
    }

}