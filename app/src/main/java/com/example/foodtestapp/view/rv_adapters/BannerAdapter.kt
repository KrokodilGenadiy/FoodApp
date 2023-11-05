package com.example.foodtestapp.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.foodtestapp.R
import com.example.foodtestapp.data.entity.Banner
import com.example.foodtestapp.databinding.BannerItemBinding

class BannerAdapter() :
    ListAdapter<Banner, BannerAdapter.ViewHolder>(BannerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(private val binding: BannerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Banner) {
            Glide.with(binding.root)
                .load(item.imageRes)
                .centerCrop()
                .placeholder(R.drawable.placeholder_banner)
                .into(binding.image)
        }
    }

    class BannerDiffCallback : DiffUtil.ItemCallback<Banner>() {
        override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
            return oldItem == newItem
        }
    }
}

