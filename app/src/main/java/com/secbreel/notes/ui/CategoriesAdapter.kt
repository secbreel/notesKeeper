package com.secbreel.notes.ui

import android.app.Application
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import com.secbreel.notes.R
import com.secbreel.notes.di.app
import com.secbreel.notes.model.Category
import kotlinx.android.synthetic.main.activity_application.view.*

class CategoriesAdapter(private val initialCategoriesList : List<Category> = listOf(), private val binding : (View, Category) -> Unit) : BaseAdapter() {

    private val categoriesList : MutableList<Category> = initialCategoriesList.toMutableList()
    fun submitList(list : List<Category>) {
        categoriesList.clear()
        categoriesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = categoriesList.count()

    override fun getItem(position: Int) = categoriesList[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: View.inflate(parent?.context, R.layout.item_category, null)
        val item = categoriesList[position]

        binding(view, item)
        return view
    }

}