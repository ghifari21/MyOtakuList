package com.gosty.myotakulist.anime.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gosty.myotakulist.R
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.databinding.ActivityDetailAnimeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailAnimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAnimeBinding
    private val detailAnimeViewModel: DetailAnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail_anime)

        val anime = intent.getParcelableExtra<Anime>(EXTRA_DATA) as Anime
        setData(anime)
    }

    private fun setData(anime: Anime) {
        binding.apply {
            Glide.with(this@DetailAnimeActivity)
                .load(anime.imageJpeg ?: anime.imageWebp)
                .apply(
                    RequestOptions
                        .placeholderOf(com.gosty.myotakulist.core.R.drawable.ic_loading)
                        .error(com.gosty.myotakulist.core.R.drawable.ic_broken_image)
                )
                .into(ivCoverAnime)

            tvTitleDefault.text = anime.title
            tvTitleEnglish.text = anime.englishTitle ?: getString(R.string.label_unknown)
            tvRankedValue.text =
                getString(R.string.value_with_hashtag, (anime.rank ?: 0).toString())
            tvScoreValue.text = (anime.score ?: 0).toString()
            tvPopularityValue.text =
                getString(R.string.value_with_hashtag, (anime.popularity ?: 0).toString())
            tvTitleJapaneseValue.text = getString(
                R.string.value_with_colon,
                (anime.japaneseTitle ?: getString(R.string.label_unknown))
            )
            tvTypeValue.text = getString(
                R.string.value_with_colon,
                (anime.type ?: getString(R.string.label_unknown))
            )
            tvEpisodesValue.text =
                getString(R.string.value_with_colon, (anime.episodes ?: 0).toString())
            tvStatusValue.text = getString(
                R.string.value_with_colon,
                (anime.status ?: getString(R.string.label_unknown))
            )
            tvAiredValue.text = getString(
                R.string.value_with_colon,
                (anime.aired.prop ?: getString(R.string.label_unknown))
            )
            val premiered = if (anime.season != null && anime.year != null) {
                "${
                    (anime.season as String).replaceFirstChar { char ->
                        char.uppercase()
                    }
                } ${anime.year}"
            } else {
                getString(R.string.label_unknown)
            }
            tvPremieredValue.text = getString(R.string.value_with_colon, premiered)
            tvBroadcastValue.text = getString(
                R.string.value_with_colon,
                (anime.broadcast.string ?: getString(R.string.label_unknown))
            )
            tvSourceValue.text = getString(
                R.string.value_with_colon,
                (anime.source ?: getString(R.string.label_unknown))
            )

            val genres = ArrayList<String>()
            anime.genres.map {
                genres.add(it.name)
            }
            tvGenresValue.text = getString(
                R.string.value_with_colon, genres.joinToString(
                    separator = ", "
                )
            )

            val themes = ArrayList<String>()
            anime.themes.map {
                themes.add(it.name)
            }
            tvThemeValue.text = getString(
                R.string.value_with_colon, themes.joinToString(
                    separator = ", "
                )
            )
            tvDurationValue.text = getString(
                R.string.value_with_colon,
                (anime.duration ?: getString(R.string.label_unknown))
            )
            tvRatingValue.text = getString(
                R.string.value_with_colon,
                (anime.rating ?: getString(R.string.label_unknown))
            )
            tvSynopsisValue.text = anime.synopsis ?: getString(R.string.label_unknown)

            var isFavorite = anime.isFavorite
            setIconFavorite(isFavorite)
            binding.fabFavorite.setOnClickListener {
                isFavorite = !isFavorite
                setIconFavorite(isFavorite)
                if (isFavorite) {
                    detailAnimeViewModel.insertAnimeFavorite(anime)
                } else {
                    detailAnimeViewModel.deleteAnimeFavorite(anime.malId)
                }
            }
        }
    }

    private fun setIconFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_menu_favorite
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> false
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}