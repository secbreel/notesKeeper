package com.secbreel.notes.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.secbreel.notes.model.Category
import com.secbreel.notes.model.Note

@Database(
    entities = [Category::class, Note::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase(){
    abstract fun categoryDao() : CategoryDAO
    abstract fun noteDao() : NoteDAO
}