package com.secbreel.notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.secbreel.notes.ui.ListItem

@Entity
class Note(val title: String, var text: String, val date: String, var categoryId: Int?) :
    ListItem() {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    override fun getType(): Int {
        return TYPE_NOTE
    }
}