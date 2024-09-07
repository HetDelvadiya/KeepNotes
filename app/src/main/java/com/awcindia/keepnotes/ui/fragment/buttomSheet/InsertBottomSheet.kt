package com.awcindia.keepnotes.ui.fragment.buttomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.awcindia.keepnotes.databinding.FragmentInsertBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InsertBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentInsertBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout
        binding = FragmentInsertBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set button click listeners
        binding.takeImage.setOnClickListener {
            // Handle Take Image click
        }

        binding.pickImage.setOnClickListener {
            // Handle Pick Image click
        }

        binding.drawing.setOnClickListener {
            // Handle Drawing click
        }
    }
}
