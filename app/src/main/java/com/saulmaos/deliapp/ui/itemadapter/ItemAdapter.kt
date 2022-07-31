package com.saulmaos.deliapp.ui.itemadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saulmaos.deliapp.data.api.responses.CategoryResponse
import com.saulmaos.deliapp.data.api.responses.MealResponse
import com.saulmaos.deliapp.databinding.ViewHolderItemBinding
import com.saulmaos.deliapp.ui.categories.CategoryDiffUtil
import com.saulmaos.deliapp.ui.categories.CategoryViewHolder
import com.saulmaos.deliapp.ui.meals.MealViewHolder

open class ItemAdapter<T>(
    private val diffUtil: DiffUtil.ItemCallback<T>,
    private val onClick: (id: String) -> Unit
): ListAdapter<T, RecyclerView.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (diffUtil is CategoryDiffUtil) {
            val binding =
                ViewHolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryViewHolder(binding, onClick)
        }
        val binding =
            ViewHolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CategoryViewHolder) holder.bind(getItem(position) as CategoryResponse)
        else if (holder is MealViewHolder) holder.bind(getItem(position) as MealResponse)
    }
}
