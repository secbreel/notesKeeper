package com.secbreel.notes.persistance

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.secbreel.notes.model.Category
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface CategoryDAO {
    @Insert
    fun insert(categories : List<Category>) : Completable

    @Query("SELECT * FROM category")
    fun observeAll() : Observable<List<Category>>

    @Delete
    fun delete(category : Category) : Completable

    @Query("DELETE FROM category")
    fun clear() : Completable

    @Insert
    fun insert(category: Category) : Completable

    @Query("Update Category SET notesCount = :notesCount WHERE id = :categoryId")
    fun updateNotesCount(categoryId : Int, notesCount : Int)
}