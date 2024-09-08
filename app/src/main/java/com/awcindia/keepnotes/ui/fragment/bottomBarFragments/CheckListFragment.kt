package com.awcindia.keepnotes.ui.fragment.bottomBarFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awcindia.keepnotes.adapter.ChecklistAdapter
import com.awcindia.keepnotes.databinding.FragmentCheckListBinding
import com.awcindia.keepnotes.ui.fragment.buttomSheet.BackgroundFragment
import com.example.notesapp.ChecklistItem


class CheckListFragment : Fragment() {

    private lateinit var binding: FragmentCheckListBinding
    private lateinit var adapter: ChecklistAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentCheckListBinding.inflate(inflater, container, false)

        val checklist = mutableListOf(ChecklistItem("", false))
        adapter = ChecklistAdapter(checklist)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Button to add new checklist item
        val addButton = binding.addButton
        addButton.setOnClickListener {
            adapter.addItem()
        }

        binding.background.setOnClickListener {
            val backgroundSheet = BackgroundFragment()
            backgroundSheet.onColorSelected =
                { selectedImageRes -> binding.main.setBackgroundResource(selectedImageRes) }
            backgroundSheet.show(childFragmentManager, "BBackgroundSheet")
        }

        return binding.root
    }
}