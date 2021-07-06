package com.secbreel.notes.model

import androidx.room.ColumnInfo

class NoteWithCategoryName (
    @ColumnInfo(name = "category_name") var categoryName: String,
    var title: String,
    var text: String,
    var date: String
) {
}