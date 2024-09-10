package com.awcindia.keepnotes.viewModel.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.awcindia.keepnotes.repository.NotesRepository

class NotesFactory(private val notesRepository: NotesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepository) as T
    }
}