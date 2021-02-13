package com.android.myapplication.movies.ui.detail.fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.android.myapplication.movies.R
import com.android.myapplication.movies.databinding.ItemCastBinding
import com.android.myapplication.movies.listenrs.PhotoItemListener
import com.android.myapplication.movies.models.Photo

class CastAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var listeners: PhotoItemListener? = null

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Photo>() {

        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemCastBinding>(
            LayoutInflater.from(parent.context), R.layout.item_cast, parent, false
        )
        return CastViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CastViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Photo>) {
        differ.submitList(list)
    }

    internal inner class CastViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getInstance(parent: ViewGroup): CastViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCastBinding.inflate(inflater, parent, false)
            return CastViewHolder(
                binding
            )
        }


        fun bind(cast: Photo) {
            binding.cast = cast
            binding.detailPhoto.setOnClickListener {
                if (listeners != null) {
                    listeners!!.onItemClick(cast)
                }
            }
        }
    }

}