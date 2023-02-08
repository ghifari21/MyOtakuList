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
import com.gosty.myotakulist.core.domain.model.manga.Manga

class MangaPagingAdapter : PagingDataAdapter<Manga, MangaPagingAdapter.MangaViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val binding =
            ItemListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MangaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        val manga = getItem(position)
        if (manga != null) {
            holder.bind(manga)
            holder.binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(manga)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(manga: Manga)
    }

    class MangaViewHolder(val binding: ItemListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(manga: Manga) {
            val image = manga.imageJpeg ?: manga.imageWebp
            binding.apply {
                Glide.with(itemView.context)
                    .load(image)
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(ivItemCover)

                tvTitle.text = manga.title
                tvContentTitle.text = itemView.context.getString(R.string.title_volume)
                tvScoreValue.text =
                    itemView.context.getString(R.string.item_value, (manga.score ?: 0).toString())
                tvRankValue.text =
                    itemView.context.getString(R.string.rank_value, (manga.rank ?: 0).toString())
                tvContentValue.text =
                    itemView.context.getString(R.string.item_value, (manga.volumes ?: 0).toString())
                tvStatusValue.text = itemView.context.getString(R.string.item_value, manga.status)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Manga> =
            object : DiffUtil.ItemCallback<Manga>() {
                override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
                    return oldItem.malId == newItem.malId
                }

                override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
                    return oldItem == newItem
                }
            }
    }
}