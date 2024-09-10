package com.awcindia.keepnotes.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.awcindia.keepnotes.dao.NotesDao
import com.awcindia.keepnotes.model.NotesModel

class NotesRepository(private val notesDao: NotesDao) {

    fun getNotes(): LiveData<PagingData<NotesModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, // Adjust the page size as needed
                enablePlaceholders = false
            ),
            pagingSourceFactory = { notesDao.getNotes() }
        ).liveData
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