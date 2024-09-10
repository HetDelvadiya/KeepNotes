package com.awcindia.keepnotes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.awcindia.keepnotes.model.NotesModel

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getNotes(): LiveData<List<NotesModel>>

    @Insert
    suspend fun insertNotes(notesModel: NotesModel)

    @Update
    suspend fun updateNotes(notesModel: NotesModel)

    @Delete
    suspend fun deleteNotes(notesModel: NotesModel)
}