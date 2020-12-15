package com.secbreel.notes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.TextView


class CategoriesListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_categories_list, null)
        val categoriesGrid = rootView.findViewById<GridView>(R.id.categoriesGrid)
        val categories = listOf(
            Category("Recepies", 1),
            Category("Books", 3),
            Category("Airsoft", 2)
        )
        categoriesGrid.adapter = CategoriesAdapter(categories) { view, category ->
            view.findViewById<TextView>(R.id.categoryTitle).text = category.title
            view.findViewById<TextView>(R.id.notesCount).text = "${category.notesCount}"
        }

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