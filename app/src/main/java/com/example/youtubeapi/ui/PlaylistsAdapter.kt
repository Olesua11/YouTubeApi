package com.example.youtubeapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.data.Constants
import com.example.youtubeapi.databinding.ItemPlaylistBinding

class PlaylistsAdapter(private val onClick: ((String) -> Unit)? = null) : ListAdapter<Item, PlaylistsAdapter.ItemPlaylistViewHolder>(DIFF_UTIL_CALL_BACK) {

    private companion object {
        val DIFF_UTIL_CALL_BACK: DiffUtil.ItemCallback<Item> =
            object : DiffUtil.ItemCallback<Item>() {
                override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onBindViewHolder(holder: ItemPlaylistViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPlaylistViewHolder {
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemPlaylistViewHolder(binding)
    }

    inner class ItemPlaylistViewHolder(private val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val item = getItem(adapterPosition)
                item?.let {
                    onClick?.invoke(it.id)
                }
            }
        }

        fun bind(item: Item?) {
            item?.let { currentItem ->
                binding.apply {
                    tvPlaylistTitle.text = currentItem.snippet.title
                    "${currentItem.contentDetails.itemCount} ${Constants.VIDEO_SERIES}".apply {
                        tvVideosCount.text = this
                    }
                    ivPreview.load(currentItem.snippet.thumbnails.standard.url)
                }
            }
        }
    }
}
