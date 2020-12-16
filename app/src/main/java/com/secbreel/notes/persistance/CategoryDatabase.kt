package com.secbreel.notes.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.secbreel.notes.model.Category

@Database(
    entities = [Category::class],
    version = 1,
    exportSchema = false
)

abstract class CategoryDatabase : RoomDatabase(){
    abstract fun categoryDao() : CategoryDAO
}