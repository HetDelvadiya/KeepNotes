package com.awcindia.keepnotes.ui.fragment.buttomSheet

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.awcindia.keepnotes.databinding.FragmentInsertBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InsertBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentInsertBottomSheetBinding
    private lateinit var imagePickerLauncher: ActivityResultLauncher<String>
    private var imageSelectedListener: OnImageSelectedListener? = null

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

        // Initialize ActivityResultLauncher
        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    imageSelectedListener?.onImageSelected(it) // Pass the URI to the activity
                }
            }

        // Set button click listeners
        binding.takeImage.setOnClickListener {
            // Handle Take Image click
        }

        binding.pickImage.setOnClickListener {
            selectImage() // Launch the image picker
        }

        binding.drawing.setOnClickListener {
            // Handle Drawing click
        }
    }

    private fun selectImage() {
        imagePickerLauncher.launch("image/*") // Open image picker
    }

    fun setOnImageSelectedListener(listener: OnImageSelectedListener) {
        imageSelectedListener = listener
    }

    interface OnImageSelectedListener {
        fun onImageSelected(uri: Uri)
    }
}
