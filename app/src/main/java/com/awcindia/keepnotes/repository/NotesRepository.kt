package com.awcindia.keepnotes.repository

import androidx.lifecycle.LiveData
import com.awcindia.keepnotes.dao.NotesDao
import com.awcindia.keepnotes.model.NotesModel

class NotesRepository(private val notesDao: NotesDao) {

    fun getNotes(): LiveData<List<NotesModel>> {
        return notesDao.getNotes()
    }


    suspend fun insertNotes(notesModel: NotesModel) {
        notesDao.insertNotes(notesModel)
    }

    suspend fun updateNotes(notesModel: NotesModel) {
        notesDao.updateNotes(notesModel)
    }

    suspend fun deleteNotes(notesModel: NotesModel) {
        notesDao.deleteNotes(notesModel)
    }
}