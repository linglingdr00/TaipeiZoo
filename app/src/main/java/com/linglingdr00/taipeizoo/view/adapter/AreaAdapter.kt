package com.linglingdr00.taipeizoo.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.linglingdr00.taipeizoo.databinding.ListAreaItemBinding
import com.linglingdr00.taipeizoo.model.data.item.AreaItem
import com.linglingdr00.taipeizoo.view.adapter.AreaAdapter.AreaViewHolder
import com.linglingdr00.taipeizoo.view.ui.AreaDetailFragment

class AreaAdapter(
    private val fragmentManager: FragmentManager
): ListAdapter<AreaItem, AreaViewHolder>(DiffCallback) {

    private val TAG = "AreaAdapter"

    companion object DiffCallback: DiffUtil.ItemCallback<AreaItem>() {

        override fun areItemsTheSame(
            oldItem: AreaItem,
            newItem: AreaItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AreaItem,
            newItem: AreaItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class AreaViewHolder(
        private val binding: ListAreaItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        // 使用 DataBinding 綁定資料
        fun bind(areaItem: AreaItem) {
            binding.areaItem = areaItem
            binding.root.setOnClickListener {
                showAreaDetailFragment(areaItem)
            }
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AreaViewHolder {
        val binding = ListAreaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AreaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    /** 顯示 area detail fragment */
    private fun showAreaDetailFragment(areaItem: AreaItem) {
        Log.d(TAG, "點擊了 $areaItem")
        AreaDetailFragment(areaItem).show(fragmentManager, "Area Detail Fragment")
    }

}