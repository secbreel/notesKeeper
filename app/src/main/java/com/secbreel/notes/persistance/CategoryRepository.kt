package com.secbreel.notes.persistance

import com.secbreel.notes.model.Category
import io.reactivex.Completable


class CategoryRepository(private val dao : CategoryDAO) {

    fun insert(categories : List<Category>) = dao.insert(categories)

    fun observeAll() = dao.observeAll()

    fun getCategoryWithNotesWithId(categoryId: Int) = dao.getCategoryWithNotesWithId(categoryId)

    fun getNoteWithCategoryName(noteId: Int) = dao.getNoteWithCategoryName(noteId)

    fun delete(category : Category) = dao.delete(category)

    fun clear() = dao.clear()

    fun insert(category: Category) = dao.insert(category)
    fun updateNotesCount(categoryId : Int, notesCount : Int) : Completable = dao.updateNotesCount(categoryId, notesCount)
}