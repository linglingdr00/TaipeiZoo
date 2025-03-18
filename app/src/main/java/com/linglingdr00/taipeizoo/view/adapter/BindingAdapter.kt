package com.linglingdr00.taipeizoo.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.chip.Chip
import com.linglingdr00.taipeizoo.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        // 將 URL string 轉換為 Uri object
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        // 將 imgUri object 的 image 載入 imgView
        imgView.load(imgUri) {
            crossfade(true)
            placeholder(R.drawable.ic_loading)
            error(R.drawable.ic_error)
        }
    }
}

@BindingAdapter("progressBarStatus")
fun bindProgressBar(progressBar: ProgressBar, status: Boolean) {
    progressBar.visibility = if (status) View.GONE else View.VISIBLE
}

@BindingAdapter("paragraphText")
fun bindParagraph(textView: TextView, text: String) {
    // 使用正則表達式將三個或更多的連續換行符替換為兩個換行符
    textView.text = text.replace(Regex("([\\r\\n])\\1{2,}"), "\n\n").trim()
}

@BindingAdapter("conservationText")
fun bindConservation(chip: Chip, text: String) {
    chip.text = text
    chip.visibility = if (text.isNotEmpty()) View.VISIBLE else View.GONE
}