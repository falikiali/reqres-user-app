package com.falikiali.reqresuserapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.falikiali.reqresuserapp.databinding.LayoutLoadStateBinding

class MainLoadStateAdapter(val onClickRetry: () -> Unit): LoadStateAdapter<MainLoadStateAdapter.MainLoadStateViewHolder>() {

    override fun onBindViewHolder(
        holder: MainLoadStateAdapter.MainLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MainLoadStateAdapter.MainLoadStateViewHolder {
        val binding = LayoutLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainLoadStateViewHolder(binding)
    }

    inner class MainLoadStateViewHolder(private val binding: LayoutLoadStateBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener { onClickRetry.invoke() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState is LoadState.Error
            }
        }
    }

}