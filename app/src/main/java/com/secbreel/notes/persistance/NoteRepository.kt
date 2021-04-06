package com.secbreel.notes.persistance

import com.secbreel.notes.model.Note


class NoteRepository(private val dao: NoteDAO) {

    fun insert(notes: List<Note>) = dao.insert(notes)

    fun observeWithCategoryId(categoryId: Int) = dao.observeWithCategoryId(categoryId)

    fun getNoteWithId(id : Int) = dao.getNoteWithId(id)

    fun observeAll() = dao.observeAll()

    fun updateNote(id: Int, title: String, text: String, date: String) =
        dao.updateNote(id, title, text, date)

    fun delete(note: Note) = dao.delete(note)

    fun clear() = dao.clear()

    fun insert(note: Note) = dao.insert(note)
}