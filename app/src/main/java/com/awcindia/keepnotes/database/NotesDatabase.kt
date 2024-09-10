package com.awcindia.keepnotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.awcindia.keepnotes.dao.NotesDao
import com.awcindia.keepnotes.model.NotesModel

@Database(entities = [NotesModel::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {

        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {

            if (INSTANCE == null) {
                synchronized(this) {

                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "NotesDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}