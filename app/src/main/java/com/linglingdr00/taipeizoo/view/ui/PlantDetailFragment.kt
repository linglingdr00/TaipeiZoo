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
import com.linglingdr00.taipeizoo.databinding.FragmentPlantDetailBinding
import com.linglingdr00.taipeizoo.model.data.item.PlantItem
import com.linglingdr00.taipeizoo.view.adapter.CarouselAdapter
import com.linglingdr00.taipeizoo.viewmodel.MainViewModel

class PlantDetailFragment(
    private val plantItem: PlantItem
) : DialogFragment() {

    companion object {
        const val TAG = "PlantDetailFragment"
    }

    private var _binding: FragmentPlantDetailBinding? = null
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
        _binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 設定 DataBinding 的 lifecycleOwner
        binding.lifecycleOwner = this
        // 設定 DataBinding 的 plantItem
        binding.plantItem = this.plantItem

        // 設定 toolbar navigation icon
        binding.plantDetailToolbar.setNavigationOnClickListener {
            dialog?.dismiss()
        }

        // 獲取 image url list
        mainViewModel.getPlantImageUrls(plantItem)
        // 觀察 image url list
        mainViewModel.plantImageUrls.observe(viewLifecycleOwner) { imgUrlList ->
            Log.d(AnimalDetailFragment.Companion.TAG, "imgUrlList: $imgUrlList")
            val strategy = if (imgUrlList.size <= 1) FullScreenCarouselStrategy() else HeroCarouselStrategy()
            // 設定 recycler view layout manager
            binding.carouselRecyclerView.layoutManager = CarouselLayoutManager(strategy)
            // 設定 recycler view adapter
            binding.carouselRecyclerView.adapter = CarouselAdapter(imgUrlList)
        }


        // 設定植物基本資訊
        binding.plantDetailInfo.text = mainViewModel.getPlantInfo(plantItem)

        // 預設選擇特徵 & 設定特徵描述
        binding.buttonToggleGroup.check(R.id.feature_button)
        binding.plantDescription.text = plantItem.fFeature

        // 設定 button toggle group
        binding.buttonToggleGroup.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            Log.d(AnimalDetailFragment.Companion.TAG, "checkedId: $checkedId, isChecked: $isChecked")
            when (checkedId) {
                R.id.feature_button -> {
                    Log.d(AnimalDetailFragment.Companion.TAG, "feature isChecked: $isChecked")
                    if (isChecked) {
                        binding.plantDescription.text = plantItem.fFeature
                    }
                    binding.featureButton.isClickable = !isChecked
                }
                R.id.function_button -> {
                    Log.d(AnimalDetailFragment.Companion.TAG, "function isChecked: $isChecked")
                    if (isChecked) {
                        binding.plantDescription.text = plantItem.fFunctionApplication
                    }
                    binding.functionButton.isClickable = !isChecked
                }
                R.id.distribution_button -> {
                    Log.d(AnimalDetailFragment.Companion.TAG, "distribution isChecked: $isChecked")
                    if (isChecked) {
                        binding.plantDescription.text = plantItem.fBrief
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