package com.hgroup.roomnote.repository

import com.hgroup.roomnote.database.NoteDao
import com.hgroup.roomnote.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    val readAllNotes = noteDao.getAllNotes()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }
}