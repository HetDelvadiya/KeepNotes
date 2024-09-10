package com.awcindia.keepnotes.ui.fragment.toggleFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.awcindia.keepnotes.adapter.NotesAdapter
import com.awcindia.keepnotes.database.NotesDatabase
import com.awcindia.keepnotes.databinding.FragmentNotesBinding
import com.awcindia.keepnotes.repository.NotesRepository
import com.awcindia.keepnotes.viewModel.notes.NotesFactory
import com.awcindia.keepnotes.viewModel.notes.NotesViewModel


class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater, container, false)

        val notesDao = NotesDatabase.getDatabase(requireContext()).notesDao()
        val notesRepository = NotesRepository(notesDao)
        notesViewModel =
            ViewModelProvider(this, NotesFactory(notesRepository))[NotesViewModel::class.java]

        notesAdapter = NotesAdapter()
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.notesRecyclerView.layoutManager = staggeredGridLayoutManager
        binding.notesRecyclerView.adapter = notesAdapter


        notesViewModel.getNotes()
        notesViewModel.notesLiveData.observe(viewLifecycleOwner, Observer { notesList ->
            notesAdapter.submitList(notesList)
        })

        return binding.root

    }
}