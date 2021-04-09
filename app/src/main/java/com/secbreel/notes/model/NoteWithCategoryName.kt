package com.secbreel.notes.model

import android.icu.text.CaseMap
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation

class NoteWithCategoryName (
    @ColumnInfo(name = "category_name") var categoryName: String,
    var title: String,
    var text: String,
    var date: String
) {
}