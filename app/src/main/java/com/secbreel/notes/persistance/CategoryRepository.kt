package com.secbreel.notes.persistance

import android.content.Context
import androidx.room.Room
import com.secbreel.notes.model.Category


class CategoryRepository(context : Context) {
    private val database = Room.databaseBuilder(context, CategoryDatabase::class.java, DB_NAME).build()
    private val dao = database.categoryDao()

    fun insert(categories : List<Category>) = dao.insert(categories)

    fun observeAll() = dao.observeAll()

    fun delete(category : Category) = dao.delete(category)

    fun clear() = dao.clear()

    fun insert(category: Category) = dao.insert(category)

    companion object {
        private const val DB_NAME = "database"
    }
}