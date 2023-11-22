package com.falikiali.reqresuserapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.falikiali.reqresuserapp.R
import com.falikiali.reqresuserapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    private lateinit var mainPagingAdapter: MainPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setStatusBar()
        initRv()
        observeDataPagination()
        observePagingAdapterLoadState()
        actionBtnRetry()
    }

    private fun setStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun initRv() {
        val loadStateAdapter = MainLoadStateAdapter(onClickRetry = {
            mainPagingAdapter.retry()
        })

        mainPagingAdapter = MainPagingAdapter()

        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainPagingAdapter.withLoadStateFooter(loadStateAdapter)
        }
    }

    private fun observeDataPagination() {
        lifecycleScope.launch {
            viewModel.users.collectLatest {
                binding.rvUser.scrollToPosition(0)
                mainPagingAdapter.submitData(it)
            }
        }
    }

    private fun observePagingAdapterLoadState() {
        lifecycleScope.launch {
            mainPagingAdapter.loadStateFlow.collectLatest {
                with(binding) {
                    progressBar.isVisible = it.refresh is LoadState.Loading
                    btnRetry.isVisible = it.refresh is LoadState.Error
                    rvUser.isVisible = it.refresh is LoadState.NotLoading
                }
            }
        }
    }

    private fun actionBtnRetry() {
        binding.btnRetry.setOnClickListener {
            mainPagingAdapter.refresh()
        }
    }

}