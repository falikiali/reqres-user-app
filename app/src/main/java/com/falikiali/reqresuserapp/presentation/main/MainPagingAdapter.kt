package com.falikiali.reqresuserapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.falikiali.reqresuserapp.databinding.ListItemUserBinding
import com.falikiali.reqresuserapp.domain.model.User

class MainPagingAdapter : PagingDataAdapter<User, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ListViewHolder(private val binding: ListItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {
            with(binding) {
                tvName.text = "${data.firstName} ${data.lastName}"
                tvEmail.text = data.email
            }

            Glide.with(itemView.context)
                .load(data.avatar)
                .into(binding.ivAvatar)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            when (holder) {
                is ListViewHolder -> holder.bind(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

}