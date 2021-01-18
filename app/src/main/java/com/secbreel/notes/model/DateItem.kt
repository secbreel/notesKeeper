package com.secbreel.notes.model

import com.secbreel.notes.ui.ListItem

class DateItem : ListItem() {
    lateinit var date : String

    override fun getType(): Int {
        return TYPE_DATE
    }
}