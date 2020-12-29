package com.secbreel.notes.ui

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.secbreel.notes.model.Category
import com.secbreel.notes.R

class CategoriesAdapter(val categoriesList : List<Category>, val binding : (View, Category) -> Unit) : BaseAdapter() {

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