package com.secbreel.notes.persistance

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.secbreel.notes.model.Category
import com.secbreel.notes.model.Note
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface NoteDAO {
    @Insert
    fun insert(notes : List<Note>) : Completable

    @Query("SELECT * FROM note where note.categoryId = :categoryId ")
    fun observeAll(categoryId : Int) : Observable<List<Note>>

    @Delete
    fun delete(note : Note) : Completable

    @Query("DELETE FROM note")
    fun clear() : Completable

    @Insert
    fun insert(note: Note) : Completable
}