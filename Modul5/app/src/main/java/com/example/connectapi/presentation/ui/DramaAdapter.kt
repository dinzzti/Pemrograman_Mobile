package com.example.connectapi.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.connectapi.databinding.ItemDramaBinding
import com.example.connectapi.domain.model.Drama

class DramaAdapter : ListAdapter<Drama, DramaAdapter.DramaViewHolder>(DramaDiffCallback()) {

    var onDetailClickListener: ((Drama) -> Unit)? = null
    var onNetflixClickListener: ((Drama) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DramaViewHolder {
        val binding = ItemDramaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DramaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DramaViewHolder, position: Int) {
        val drama = getItem(position)
        holder.bind(drama)
    }

    inner class DramaViewHolder(private val binding: ItemDramaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(drama: Drama) {
            binding.tvTitle.text = drama.name
            binding.tvOverview.text = drama.overview
            binding.tvReleaseDate.text = drama.firstAirDate

            binding.ivPoster.load("https://image.tmdb.org/t/p/w500${drama.posterPath}") {
                crossfade(true)
            }

            binding.btnDetail.setOnClickListener {
                onDetailClickListener?.invoke(drama)
            }
        }
    }

    class DramaDiffCallback : DiffUtil.ItemCallback<Drama>() {
        override fun areItemsTheSame(oldItem: Drama, newItem: Drama): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Drama, newItem: Drama): Boolean {
            return oldItem == newItem
        }
    }
}