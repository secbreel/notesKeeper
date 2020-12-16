package com.secbreel.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(val title: String, val notesCount: Int) {
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}
