package com.awcindia.keepnotes.viewModel.notes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awcindia.keepnotes.model.NotesModel
import com.awcindia.keepnotes.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    private val _notesLiveData = MediatorLiveData<List<NotesModel>>()
    val notesLiveData: LiveData<List<NotesModel>> get() = _notesLiveData

    fun getNotes() {
        _notesLiveData.addSource(notesRepository.getNotes()) { notes ->
            if (notes != null) {
                _notesLiveData.value = notes
                Log.d("noteLiveData", notes.toString())
            }
        }
    }

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