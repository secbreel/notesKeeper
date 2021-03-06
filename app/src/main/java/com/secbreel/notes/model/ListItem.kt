package com.secbreel.notes.model

abstract class ListItem {
    companion object {
        const val TYPE_DATE : Int = 0
        const val TYPE_NOTE : Int = 1
    }

    abstract fun getType() : Int
}