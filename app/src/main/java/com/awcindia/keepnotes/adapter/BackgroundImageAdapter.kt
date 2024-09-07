package com.awcindia.keepnotes.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.awcindia.keepnotes.databinding.ItemBackgroundBinding

class BackgroundImageAdapter(
    private val imageList: List<Int>,
    private val onImageClick: (Int) -> Unit,
) :
    RecyclerView.Adapter<BackgroundImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ItemBackgroundBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val image = imageList[position]
        holder.bind(image)
    }

    inner class ImageViewHolder(private val binding: ItemBackgroundBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Int) {
            // Check if the resource exists
            try {
                binding.btnPickImage.setImageResource(image)
            } catch (e: Resources.NotFoundException) {
                e.printStackTrace() // Log the error to debug
            }

            binding.cardView.setOnClickListener {
                onImageClick(image)
            }
        }

    }
}