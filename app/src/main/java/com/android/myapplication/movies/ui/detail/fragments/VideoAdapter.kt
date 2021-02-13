package com.android.myapplication.movies.ui.detail.fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.android.myapplication.movies.R
import com.android.myapplication.movies.databinding.ItemVideoBinding
import com.android.myapplication.movies.listenrs.VideoItemListener
import com.android.myapplication.movies.models.Video

class VideoAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var listeners: VideoItemListener? = null


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Video>() {

        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemVideoBinding>(
            LayoutInflater.from(parent.context), R.layout.item_video, parent, false
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

    fun submitList(list: List<Video>) {
        differ.submitList(list)
    }

    internal inner class CastViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun getInstance(parent: ViewGroup): CastViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemVideoBinding.inflate(inflater, parent, false)
            return CastViewHolder(
                binding
            )
        }


        fun bind(cast: Video) {
            binding.cast = cast
            binding.openVideo.setOnClickListener {
                if (listeners != null) {
                    listeners!!.onItemClick(cast)
                }
            }

        }
    }

}