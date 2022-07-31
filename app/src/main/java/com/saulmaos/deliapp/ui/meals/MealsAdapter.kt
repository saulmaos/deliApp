package com.saulmaos.deliapp.ui.meals

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saulmaos.deliapp.data.api.responses.MealResponse
import com.saulmaos.deliapp.databinding.ViewHolderItemBinding
import com.saulmaos.deliapp.ui.itemadapter.ItemAdapter
import com.saulmaos.deliapp.utils.useGlide

class MealsAdapter(
    onClick: (id: String) -> Unit
) : ItemAdapter<MealResponse>(MealDiffUtil(), onClick)

class MealViewHolder(
    private val binding: ViewHolderItemBinding,
    private val onClick: (id: String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mealResponse: MealResponse) {
        with(binding) {
            ivImage.useGlide(mealResponse.image)
            name.text = mealResponse.meal
            root.setOnClickListener {
                onClick(mealResponse.idMeal)
            }
        }
    }
}

class MealDiffUtil : DiffUtil.ItemCallback<MealResponse>() {
    override fun areItemsTheSame(oldItem: MealResponse, newItem: MealResponse): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: MealResponse, newItem: MealResponse): Boolean {
        return oldItem == newItem
    }
}
