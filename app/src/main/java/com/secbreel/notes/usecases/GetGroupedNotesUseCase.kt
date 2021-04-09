package com.secbreel.notes.usecases

import com.secbreel.notes.model.DateItem
import com.secbreel.notes.model.ListItem
import com.secbreel.notes.model.Note
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class GetGroupedNotesUseCase() {

    operator fun invoke(notesList: List<Note>): List<ListItem> {
        return getSortedList(getGroupedHashList(notesList))
    }


    private fun getGroupedHashList(notesList: List<Note>): HashMap<String, MutableList<Note>> {
        val groupedHashMap: HashMap<String, MutableList<Note>> = HashMap()
        for (note: Note in notesList.sortedBy { it.date }.reversed()) {

            val hashMapKey: String = convertDate(note.date)
            if (groupedHashMap.containsKey(hashMapKey)) {
                groupedHashMap[hashMapKey]?.add(note)
            } else {
                val list: MutableList<Note> = mutableListOf(note)
                groupedHashMap[hashMapKey] = list
            }
        }
        return groupedHashMap
    }


    private fun getSortedList(hashMap: HashMap<String, MutableList<Note>>): List<ListItem> {
        val sortedList: MutableList<ListItem> = mutableListOf()
        for (date in hashMap.keys) {
            var dateItem: DateItem = DateItem()
            dateItem.date = date
            sortedList.add(dateItem)
            for (note in hashMap[date]!!) {
                sortedList.add(note)
            }
        }
        return sortedList
    }


    private fun convertDate(dateString: String): String {
        val date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)
        return SimpleDateFormat("LLLL yyyy", Locale.getDefault()).format(date!!)
    }
}