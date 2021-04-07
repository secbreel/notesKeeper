package com.secbreel.notes.model

import androidx.room.Embedded
import androidx.room.Relation

class CategoryWithNotes(
    @Embedded var category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    ) var notes: MutableList<Note>
) {

}