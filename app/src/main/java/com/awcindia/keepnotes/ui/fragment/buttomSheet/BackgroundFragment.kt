package com.awcindia.keepnotes.ui.fragment.buttomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.awcindia.keepnotes.R
import com.awcindia.keepnotes.adapter.BackgroundImageAdapter
import com.awcindia.keepnotes.databinding.FragmentBackgroundBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BackgroundFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBackgroundBinding
    lateinit var backgroundImageAdapter: BackgroundImageAdapter

    private val imageList = listOf(
        R.drawable.naruto, // Replace with your actual image resource names
        R.drawable.gt,
    )


    var onColorSelected: ((Int) -> Unit)? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBackgroundBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        backgroundImageAdapter = BackgroundImageAdapter(imageList) { selectedColor ->
            onColorSelected?.invoke(selectedColor) // Pass the selected color back
            dismiss() // Close the fragment after selection
        }

        // Set up the RecyclerView
        binding.backgroundRecyclerview.apply {
            adapter = backgroundImageAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}