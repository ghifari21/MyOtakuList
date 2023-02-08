package com.gosty.myotakulist.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gosty.myotakulist.core.R
import com.gosty.myotakulist.core.databinding.ItemListLayoutBinding
import com.gosty.myotakulist.core.domain.model.anime.Anime

class AnimePagingAdapter :
    PagingDataAdapter<Anime, AnimePagingAdapter.AnimeViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding =
            ItemListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position)
        if (anime != null) {
            holder.bind(anime)
            holder.binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(anime)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(anime: Anime)
    }

    class AnimeViewHolder(val binding: ItemListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            val image = anime.imageJpeg ?: anime.imageWebp
            binding.apply {
                Glide.with(itemView.context)
                    .load(image)
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(ivItemCover)

                tvTitle.text = anime.title
                tvContentTitle.text = itemView.context.getString(R.string.title_episode)
                tvScoreValue.text =
                    itemView.context.getString(R.string.item_value, (anime.score ?: 0).toString())
                tvRankValue.text =
                    itemView.context.getString(R.string.rank_value, (anime.rank ?: 0).toString())
                tvContentValue.text = itemView.context.getString(
                    R.string.item_value,
                    (anime.episodes ?: 0).toString()
                )
                tvStatusValue.text = itemView.context.getString(R.string.item_value, anime.status)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Anime> =
            object : DiffUtil.ItemCallback<Anime>() {
                override fun areItemsTheSame(oldAnime: Anime, newAnime: Anime): Boolean {
                    return oldAnime.malId == newAnime.malId
                }

                override fun areContentsTheSame(oldAnime: Anime, newAnime: Anime): Boolean {
                    return oldAnime == newAnime
                }
            }
    }
}