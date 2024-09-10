package com.awcindia.keepnotes.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.awcindia.keepnotes.model.NotesModel

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY isPinned DESC , id DESC")
    fun getNotes(): PagingSource<Int, NotesModel>

    @Insert
    suspend fun insertNotes(notesModel: NotesModel)

    @Update
    suspend fun updateNotes(notesModel: NotesModel)

    @Delete
    suspend fun deleteNotes(notesModel: NotesModel)
}