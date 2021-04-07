package com.secbreel.notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "note",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = CASCADE
        )]
)
class Note(
    val title: String,
    var text: String,
    val date: String,
    @ColumnInfo(name = "category_id") var categoryId: Int?
) :
    ListItem() {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    override fun getType(): Int {
        return TYPE_NOTE
    }
}