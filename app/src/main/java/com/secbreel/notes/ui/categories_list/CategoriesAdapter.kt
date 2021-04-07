package com.secbreel.notes.ui.categories_list

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.secbreel.notes.R
import com.secbreel.notes.model.Category
import com.secbreel.notes.model.CategoryWithNotes

class CategoriesAdapter(
    private val initialCategoriesList: List<CategoryWithNotes> = listOf(),
    private val binding: (View, CategoryWithNotes) -> Unit
) : BaseAdapter() {

    private val categoriesList: MutableList<CategoryWithNotes> = initialCategoriesList.toMutableList()
    fun submitList(list: List<CategoryWithNotes>) {
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