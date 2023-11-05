package com.example.foodtestapp.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodtestapp.R
import com.example.foodtestapp.data.entity.Meal
import com.example.foodtestapp.databinding.MealItemBinding

class MealAdapter :
    ListAdapter<Meal, MealAdapter.MealViewHolder>(MealDiffCallback()) {

    class MealViewHolder(private val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            binding.name.text = meal.strMeal
            binding.description.text = meal.strInstructions
            binding.price.text = "${(100..999).random()}$"
            Glide.with(binding.root)
                .load(meal.strMealThumb)
                .placeholder(R.drawable.pizza_placeholder)
                .centerCrop()
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) =
        holder.bind(getItem(position))

    class MealDiffCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }
}


