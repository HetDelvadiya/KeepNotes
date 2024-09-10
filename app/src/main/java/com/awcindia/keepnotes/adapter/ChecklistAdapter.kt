package com.awcindia.keepnotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.awcindia.keepnotes.databinding.ItemChecklistBinding
import com.awcindia.keepnotes.model.ChecklistItem

class ChecklistAdapter(private val checklist: MutableList<ChecklistItem>) :
    RecyclerView.Adapter<ChecklistAdapter.ChecklistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistViewHolder {
        val binding =
            ItemChecklistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChecklistViewHolder(binding)
    }

    override fun getItemCount(): Int = checklist.size
    override fun onBindViewHolder(holder: ChecklistViewHolder, position: Int) {
        val item = checklist[position]

        holder.bind(item)
    }

    inner class ChecklistViewHolder(private val binding: ItemChecklistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChecklistItem) {
            binding.checkBox.isChecked = item.isChecked
            binding.editText.setText(item.text)

            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                item.isChecked = isChecked
            }

            binding.editText.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    item.text = binding.editText.text.toString()
                }
            }
        }
    }

    // Add item function
    fun addItem() {
        checklist.add(ChecklistItem("", false))
        notifyItemInserted(checklist.size - 1)
    }
}
