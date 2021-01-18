package com.secbreel.notes.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.secbreel.notes.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)

abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDAO
}