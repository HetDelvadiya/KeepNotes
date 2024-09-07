package com.awcindia.keepnotes.ui.fragment.bottomBarFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.awcindia.keepnotes.databinding.FragmentDrawBinding
import com.awcindia.keepnotes.ui.view.PaintView


class DrawFragment : Fragment() {

    private lateinit var paintView: PaintView
    private lateinit var binding: FragmentDrawBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDrawBinding.inflate(inflater, container, false)

        paintView = binding.paintView
        return binding.root
    }
}