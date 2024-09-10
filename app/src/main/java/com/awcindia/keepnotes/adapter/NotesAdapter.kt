package com.awcindia.keepnotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.awcindia.keepnotes.databinding.ListNotesLayoutBinding
import com.awcindia.keepnotes.model.NotesModel

class NotesAdapter : ListAdapter<NotesModel, NotesAdapter.NotesViewHolder>(NotesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding =
            ListNotesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

    inner class NotesViewHolder(private val binding: ListNotesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NotesModel) {
            binding.notesTitle.text = note.title
            binding.notesDiscription.text = note.description
            note.backgroundImage?.let { binding.main.setBackgroundResource(it) }
        }
    }
}


object NotesDiffCallback : DiffUtil.ItemCallback<NotesModel>() {
    override fun areItemsTheSame(oldItem: NotesModel, newItem: NotesModel): Boolean {
        // Compare items based on unique identifier
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NotesModel, newItem: NotesModel): Boolean {
        // Compare items based on content
        return oldItem == newItem
    }
}

