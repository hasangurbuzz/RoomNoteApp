package com.hgroup.roomnote.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hgroup.roomnote.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM NOTE_TABLE")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<Note>>
}