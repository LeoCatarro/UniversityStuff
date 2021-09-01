package com.example.tomaybedoapp.repository

import androidx.lifecycle.LiveData
import com.example.tomaybedoapp.db.NoteDao
import com.example.tomaybedoapp.models.Note

class NoteRepository(private val noteDao: NoteDao) {

    val listAllNotes: LiveData<List<Note>> = noteDao.listAllNotes()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }
}