package com.linglingdr00.taipeizoo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.linglingdr00.taipeizoo.R
import com.linglingdr00.taipeizoo.databinding.ListCarouselItemBinding

class CarouselAdapter(
    private val imgUrlList: List<String>
): RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ListCarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        // 取得圖片網址
        val imgUrl = imgUrlList[position]
        holder.bind(imgUrl)
    }

    override fun getItemCount(): Int = imgUrlList.size

    class CarouselViewHolder(
        private val binding: ListCarouselItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(imgUrl: String) {
            // 將 URL string 轉換為 Uri object
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            // 將 imgUri object 的 image 載入 imgView
            binding.carouselImageView.load(imgUri) {
                crossfade(true)
                placeholder(R.drawable.ic_loading)
                error(R.drawable.ic_error)
            }
        }
    }

}