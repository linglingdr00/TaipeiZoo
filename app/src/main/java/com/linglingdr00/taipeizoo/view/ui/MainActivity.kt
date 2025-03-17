package com.linglingdr00.taipeizoo.view.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.linglingdr00.taipeizoo.R
import com.linglingdr00.taipeizoo.databinding.ActivityMainBinding
import com.linglingdr00.taipeizoo.utils.NetworkState
import com.linglingdr00.taipeizoo.view.adapter.AreaAdapter
import com.linglingdr00.taipeizoo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    // view binding
    private lateinit var binding: ActivityMainBinding
    // viewmodel
    private val mainViewModel: MainViewModel by viewModels()
    // adapter
    private val areaAdapter = AreaAdapter(supportFragmentManager)

    // network state
    private lateinit var networkState: NetworkState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 監聽網路狀態
        networkState = NetworkState(this)
        networkState.observe(this) {
            if (it) {
                // 顯示 progress bar
                binding.mainProgressBar.visibility = View.VISIBLE
                // 抓取資料
                mainViewModel.getAllData()
            } else {
                // 隱藏 progress bar
                binding.mainProgressBar.visibility = View.GONE
            }
        }

        // 設定 DataBinding
        binding.lifecycleOwner = this
        binding.mainViewModel = this.mainViewModel

        // 設定 RecyclerView (2列的 GridLayout)
        binding.areaRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.areaRecyclerView.adapter = areaAdapter

        // 觀察 areaItems，當資料改變時更新 adapter
        mainViewModel.areaItems.observe(this) {
            areaAdapter.submitList(it)
        }

    }
}