package com.saulmaos.deliapp.ui.categories

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saulmaos.deliapp.data.api.responses.CategoryResponse
import com.saulmaos.deliapp.databinding.ViewHolderItemBinding
import com.saulmaos.deliapp.ui.itemadapter.ItemAdapter
import com.saulmaos.deliapp.utils.useGlide

class CategoriesAdapter(
    onClick: (id: String) -> Unit
) : ItemAdapter<CategoryResponse>(CategoryDiffUtil(), onClick)

class CategoryViewHolder(
    private val binding: ViewHolderItemBinding,
    private val onClick: (id: String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(categoryResponse: CategoryResponse) {
        with(binding) {
            ivImage.useGlide(categoryResponse.image)
            name.text = categoryResponse.category
            root.setOnClickListener {
                onClick(categoryResponse.category)
            }
        }
    }
}

class CategoryDiffUtil : DiffUtil.ItemCallback<CategoryResponse>() {
    override fun areItemsTheSame(oldItem: CategoryResponse, newItem: CategoryResponse): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }

    override fun areContentsTheSame(oldItem: CategoryResponse, newItem: CategoryResponse): Boolean {
        return oldItem == newItem
    }
}
