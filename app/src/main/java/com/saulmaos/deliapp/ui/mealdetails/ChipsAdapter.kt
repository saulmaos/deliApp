package com.saulmaos.deliapp.ui.mealdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saulmaos.deliapp.databinding.ViewHolderChipBinding

class ChipsAdapter: ListAdapter<String, ChipViewHolder>(ChipDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val binding =
            ViewHolderChipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ChipViewHolder(
    private val binding: ViewHolderChipBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(name: String) {
        binding.chip.text = name
    }
}

class ChipDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
