package com.loan555.roomdemo.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.loan555.roomdemo.database.NoteDatabase
import com.loan555.roomdemo.database.dao.NoteDao
import com.loan555.roomdemo.model.Note

class NoteRepository(application: Application) {
    private val noteDao: NoteDao

    init {
        val noteDatabase: NoteDatabase = NoteDatabase.getInstance(application)
        noteDao = noteDatabase.getNoteDao()
    }

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    fun getAllNote(): LiveData<List<Note>> = noteDao.getAllNote()
}