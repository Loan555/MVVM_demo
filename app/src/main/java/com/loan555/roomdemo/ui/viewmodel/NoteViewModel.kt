package com.loan555.roomdemo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.loan555.roomdemo.database.repository.NoteRepository
import com.loan555.roomdemo.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : ViewModel() {
    private val noteRepository: NoteRepository = NoteRepository(application)

    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun getAllNote(): LiveData<List<Note>> = noteRepository.getAllNote()

    class NoteViewModelFactory(private val application: Application):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)){// kiem tra xem no co phai laf bieu hien cua doi tuong...
                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(application) as T
            }
            throw IllegalAccessException("Unable construct viewModel")
        }

    }
}