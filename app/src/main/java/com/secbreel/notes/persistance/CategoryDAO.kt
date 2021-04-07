package com.secbreel.notes.persistance

import androidx.room.*
import com.secbreel.notes.model.Category
import com.secbreel.notes.model.CategoryWithNotes
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface CategoryDAO {
    @Insert
    fun insert(categories : List<Category>) : Completable

    @Transaction
    @Query("SELECT * FROM category")
    fun observeAll() : Observable<List<CategoryWithNotes>>

    @Transaction
    @Query("SELECT * From category where id = :categoryId")
    fun getCategoryWithNotesWithId(categoryId: Int) : Observable<CategoryWithNotes>

    @Delete
    fun delete(category : Category) : Completable

    @Query("DELETE FROM category")
    fun clear() : Completable

    @Insert
    fun insert(category: Category) : Completable

    @Query("Update Category SET notesCount = :notesCount WHERE id = :categoryId")
    fun updateNotesCount(categoryId : Int, notesCount : Int) : Completable
}