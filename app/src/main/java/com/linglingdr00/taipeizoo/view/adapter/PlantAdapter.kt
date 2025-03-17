package com.linglingdr00.taipeizoo.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.linglingdr00.taipeizoo.databinding.ListPlantItemBinding
import com.linglingdr00.taipeizoo.model.data.item.PlantItem
import com.linglingdr00.taipeizoo.view.adapter.PlantAdapter.PlantViewHolder
import com.linglingdr00.taipeizoo.view.ui.PlantDetailFragment

class PlantAdapter(
    private val fragmentManager: FragmentManager
): ListAdapter<PlantItem, PlantViewHolder>(DiffCallback) {

    private val TAG = "PlantAdapter"

    companion object DiffCallback: DiffUtil.ItemCallback<PlantItem>() {

        override fun areItemsTheSame(
            oldItem: PlantItem,
            newItem: PlantItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PlantItem,
            newItem: PlantItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    inner class PlantViewHolder(
        private val binding: ListPlantItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        // 使用 DataBinding 綁定資料
        fun bind(plantItem: PlantItem) {
            binding.plantItem = plantItem
            binding.root.setOnClickListener {
                showPlantDetailFragment(plantItem)
            }
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlantViewHolder {
        val binding = ListPlantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    /** 顯示 plant detail fragment */
    private fun showPlantDetailFragment(plantItem: PlantItem) {
        Log.d(TAG, "點擊了 $plantItem")
        PlantDetailFragment(plantItem).show(fragmentManager, "Plant Detail Fragment")
    }

}