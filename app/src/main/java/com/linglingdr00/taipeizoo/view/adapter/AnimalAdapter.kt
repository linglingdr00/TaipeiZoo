package com.linglingdr00.taipeizoo.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.linglingdr00.taipeizoo.databinding.ListAnimalItemBinding
import com.linglingdr00.taipeizoo.model.data.item.AnimalItem
import com.linglingdr00.taipeizoo.view.ui.AnimalDetailFragment

class AnimalAdapter(
    private val fragmentManager: FragmentManager
): ListAdapter<AnimalItem, AnimalAdapter.AnimalViewHolder>(DiffCallback) {

    private val TAG = "AnimalAdapter"

    companion object DiffCallback: DiffUtil.ItemCallback<AnimalItem>() {

        override fun areItemsTheSame(
            oldItem: AnimalItem,
            newItem: AnimalItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AnimalItem,
            newItem: AnimalItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    inner class AnimalViewHolder(
        private val binding: ListAnimalItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        // 使用 DataBinding 綁定資料
        fun bind(animalItem: AnimalItem) {
            binding.animalItem = animalItem
            binding.root.setOnClickListener {
                showAnimalDetailFragment(animalItem)
            }
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimalViewHolder {
        val binding = ListAnimalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    /** 顯示 animal detail fragment */
    private fun showAnimalDetailFragment(animalItem: AnimalItem) {
        Log.d(TAG, "點擊了 $animalItem")
        AnimalDetailFragment(animalItem).show(fragmentManager, "Animal Detail Fragment")
    }

}