package com.awcindia.keepnotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.awcindia.keepnotes.utils.Converters
import java.util.Date

@Entity(tableName = "notes")
@TypeConverters(Converters::class) // Converters for complex types like Date and List<String>
data class NotesModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0, // Unique identifier for each note
    var title: String, // Title of the note
    var description: String, // Description or body of the note
    var images: String = "", // List of image URIs or paths added to the note
    var backgroundImage: Int? = null, // URI or path of the background image for the note
    var isPinned: Boolean = false, // Indicates if the note is pinned
    var isArchive: Boolean = false,// Indicates if the notes is archive
    var reminder: Boolean = false, // Reminder date and time, if set
    var createdAt: Date = Date(), // Date and time when the note was created
    var updatedAt: Date = Date(), // Date and time when the note was last updated
) {

}