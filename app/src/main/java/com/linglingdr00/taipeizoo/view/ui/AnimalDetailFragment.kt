package com.linglingdr00.taipeizoo.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.google.android.material.carousel.HeroCarouselStrategy
import com.linglingdr00.taipeizoo.R
import com.linglingdr00.taipeizoo.databinding.FragmentAnimalDetailBinding
import com.linglingdr00.taipeizoo.model.data.item.AnimalItem
import com.linglingdr00.taipeizoo.view.adapter.CarouselAdapter
import com.linglingdr00.taipeizoo.viewmodel.MainViewModel

class AnimalDetailFragment(
    private val animalItem: AnimalItem
): DialogFragment() {

    companion object {
        const val TAG = "AnimalDetailFragment"
    }

    private var _binding: FragmentAnimalDetailBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Custom_Dialog_Fullscreen)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimalDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 設定 DataBinding 的 lifecycleOwner
        binding.lifecycleOwner = this
        // 設定 DataBinding 的 animalItem
        binding.animalItem = this.animalItem

        // 設定 toolbar navigation icon
        binding.animalDetailToolbar.setNavigationOnClickListener {
            dialog?.dismiss()
        }

        // 獲取 image url list
        mainViewModel.getAnimalImageUrls(animalItem)
        // 觀察 image url list
        mainViewModel.animalImageUrls.observe(viewLifecycleOwner) { imgUrlList ->
            Log.d(TAG, "imgUrlList: $imgUrlList")
            val strategy = if (imgUrlList.size <= 1) FullScreenCarouselStrategy() else HeroCarouselStrategy()
            // 設定 recycler view layout manager
            binding.carouselRecyclerView.layoutManager = CarouselLayoutManager(strategy)
            // 設定 recycler view adapter
            binding.carouselRecyclerView.adapter = CarouselAdapter(imgUrlList)
        }

        // 設定動物基本資訊
        binding.animalDetailInfo.text = mainViewModel.getAnimalInfo(animalItem)

        // 預設選擇特徵 & 設定特徵描述
        binding.buttonToggleGroup.check(R.id.feature_button)
        binding.animalDescription.text = animalItem.aFeature

        // 設定 button toggle group
        binding.buttonToggleGroup.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            Log.d(TAG, "checkedId: $checkedId, isChecked: $isChecked")
            when (checkedId) {
                R.id.feature_button -> {
                    Log.d(TAG, "feature isChecked: $isChecked")
                    if (isChecked) {
                        binding.animalDescription.text = animalItem.aFeature
                    }
                    binding.featureButton.isClickable = !isChecked
                }
                R.id.diet_and_behavior_button -> {
                    Log.d(TAG, "diet_and_behavior isChecked: $isChecked")
                    if (isChecked) {
                        val description = "${animalItem.aDiet}\n\n${animalItem.aBehavior}"
                        binding.animalDescription.text = description
                    }
                    binding.dietAndBehaviorButton.isClickable = !isChecked
                }
                R.id.distribution_button -> {
                    Log.d(TAG, "distribution isChecked: $isChecked")
                    if (isChecked) {
                        binding.animalDescription.text = animalItem.aDistribution
                    }
                    binding.distributionButton.isClickable = !isChecked
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        // 設定 dialog fullscreen
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}