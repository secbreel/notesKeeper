package com.secbreel.notes.persistance

import com.secbreel.notes.model.Note


class NoteRepository(private val dao : NoteDAO) {

    fun insert(notes : List<Note>) = dao.insert(notes)

    fun observeAll(categoryId : Int) = dao.observeAll(categoryId)

    fun delete(note : Note) = dao.delete(note)

    fun clear() = dao.clear()

    fun insert(note: Note) = dao.insert(note)
}