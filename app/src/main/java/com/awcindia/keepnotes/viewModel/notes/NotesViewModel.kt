package com.awcindia.keepnotes.viewModel.notes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.awcindia.keepnotes.model.NotesModel
import com.awcindia.keepnotes.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    val notesLiveData: LiveData<PagingData<NotesModel>> = notesRepository.getNotes()

    fun insertNotes(notesModel: NotesModel) {
        viewModelScope.launch {
            notesRepository.insertNotes(notesModel)
        }
    }

    fun updateNotes(notesModel: NotesModel) {
        viewModelScope.launch {
            notesRepository.updateNotes(notesModel)
        }
    }

    fun deleteNotes(notesModel: NotesModel) {
        viewModelScope.launch {
            notesRepository.deleteNotes(notesModel)
        }
    }
}