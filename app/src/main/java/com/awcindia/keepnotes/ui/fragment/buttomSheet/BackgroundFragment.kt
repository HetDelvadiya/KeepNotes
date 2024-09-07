package com.awcindia.keepnotes.ui.fragment.buttomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.awcindia.keepnotes.databinding.FragmentBackgroundBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BackgroundFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBackgroundBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBackgroundBinding.inflate(inflater, container, false)
        return binding.root
    }

}