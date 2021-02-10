package com.secbreel.notes.model

class DateItem : ListItem() {
    lateinit var date : String

    override fun getType(): Int {
        return TYPE_DATE
    }
}