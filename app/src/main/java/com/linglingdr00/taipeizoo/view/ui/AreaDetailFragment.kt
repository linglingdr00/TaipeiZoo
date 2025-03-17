package com.linglingdr00.taipeizoo.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.linglingdr00.taipeizoo.R
import com.linglingdr00.taipeizoo.databinding.FragmentAreaDetailBinding
import com.linglingdr00.taipeizoo.model.data.item.AreaItem
import com.linglingdr00.taipeizoo.view.adapter.AnimalAdapter
import com.linglingdr00.taipeizoo.view.adapter.PlantAdapter
import com.linglingdr00.taipeizoo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AreaDetailFragment(
    private val areaItem: AreaItem
): DialogFragment() {

    companion object {
        private const val TAG = "AreaDetailDialog"
    }

    private var _binding: FragmentAreaDetailBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var plantAdapter: PlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Custom_Dialog_Fullscreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAreaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 設定 DataBinding 的 lifecycleOwner
        binding.lifecycleOwner = this
        // 設定 DataBinding 的 mainViewModel
        binding.mainViewModel = this.mainViewModel
        // 設定 DataBinding 的 areaItem
        binding.areaItem = this.areaItem

        animalAdapter = AnimalAdapter(childFragmentManager)
        plantAdapter = PlantAdapter(childFragmentManager)

        // 設定 animal recycler view
        val animalLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.animalRecyclerView.layoutManager = animalLayoutManager
        // 設定 animal recycler view 的 adapter
        binding.animalRecyclerView.adapter = animalAdapter

        // 設定 plant recycler view
        val plantLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.plantRecyclerView.layoutManager = plantLayoutManager
        // 設定 plant recycler view 的 adapter
        binding.plantRecyclerView.adapter = plantAdapter

        binding.areaDetailToolbar.setNavigationOnClickListener {
            dialog?.dismiss()
        }

        binding.areaDetailToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.open_in_browser -> {
                    // 開啟網頁
                    openInBrowser(areaItem.eUrl)
                    true
                }
                else -> false
            }
        }

        // 資料載入完成後，從 db 根據館區名稱獲取動物和植物資料
        mainViewModel.loadAllDataStatus.observe(viewLifecycleOwner) { isFinish ->
            Log.d(TAG, "loadAllDataStatus: $isFinish")
            if (isFinish) {
                mainViewModel.getAnimalItemsByArea(areaItem.eName)
                mainViewModel.getPlantItemsByArea(areaItem.eName)
            }
        }

        // 觀察 animalItems，當資料改變時更新 adapter
        mainViewModel.animalItems.observe(viewLifecycleOwner) {
            animalAdapter.submitList(it)
        }

        // 觀察 plantItems，當資料改變時更新 adapter
        mainViewModel.plantItems.observe(viewLifecycleOwner) {
            plantAdapter.submitList(it)
        }

    }

    private fun openInBrowser(url: String) {
        // 創建 Intent 用於打開瀏覽器
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        // 啟動外部瀏覽器
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        // 設定 dialog fullscreen
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }

    override fun dismiss() {
        super.dismiss()
        // 清空 animalItems 和 plantItems
        mainViewModel.clearAnimalItems()
        mainViewModel.clearPlantItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}