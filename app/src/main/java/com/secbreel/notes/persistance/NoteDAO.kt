package com.secbreel.notes.persistance

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.secbreel.notes.model.Note
import com.secbreel.notes.model.NoteWithCategoryName
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface NoteDAO {
    @Insert
    fun insert(notes: List<Note>): Completable

    @Query("SELECT * FROM note")
    fun observeAll(): Observable<List<Note>>

    @Query("SELECT * FROM note where note.category_id = :categoryId ")
    fun observeWithCategoryId(categoryId: Int): Observable<List<Note>>

    @Query("UPDATE note SET title = :title, text = :text, date = :date where id = :id")
    fun updateNote(id: Int, title: String, text: String, date: String): Completable

    @Delete
    fun delete(note: Note): Completable

    @Query("DELETE FROM note")
    fun clear(): Completable

    @Insert
    fun insert(note: Note): Completable
}