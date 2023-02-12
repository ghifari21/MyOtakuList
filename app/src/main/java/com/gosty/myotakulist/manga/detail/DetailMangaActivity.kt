package com.gosty.myotakulist.manga.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gosty.myotakulist.R
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.databinding.ActivityDetailMangaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMangaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMangaBinding
    private val detailMangaViewModel: DetailMangaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMangaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail_manga)

        val manga = intent.getParcelableExtra<Manga>(EXTRA_DATA) as Manga
        setData(manga)
    }

    private fun setData(manga: Manga) {
        binding.apply {
            Glide.with(this@DetailMangaActivity)
                .load(manga.imageJpeg ?: manga.imageWebp)
                .apply(
                    RequestOptions
                        .placeholderOf(com.gosty.myotakulist.core.R.drawable.ic_loading)
                        .error(com.gosty.myotakulist.core.R.drawable.ic_broken_image)
                )
                .into(ivCoverAnime)

            tvTitleDefault.text = manga.title
            tvTitleEnglish.text = manga.englishTitle ?: getString(R.string.label_unknown)
            tvRankedValue.text =
                getString(R.string.value_with_hashtag, (manga.rank ?: 0).toString())
            tvScoreValue.text = (manga.score ?: 0).toString()
            tvPopularityValue.text =
                getString(R.string.value_with_hashtag, (manga.popularity ?: 0).toString())
            tvTitleJapaneseValue.text = getString(
                R.string.value_with_colon,
                (manga.japaneseTitle ?: getString(R.string.label_unknown))
            )
            tvTypeValue.text = getString(
                R.string.value_with_colon,
                (manga.type ?: getString(R.string.label_unknown))
            )
            tvVolumesValue.text = getString(
                R.string.value_with_colon,
                (manga.volumes ?: 0).toString()
            )
            tvChaptersValue.text = getString(
                R.string.value_with_colon,
                (manga.chapters ?: 0).toString()
            )
            tvStatusValue.text = getString(
                R.string.value_with_colon,
                (manga.status ?: getString(R.string.label_unknown))
            )
            tvPublishedValue.text = getString(
                R.string.value_with_colon,
                (manga.published.prop ?: getString(R.string.label_unknown))
            )

            val genres = ArrayList<String>()
            manga.genres.map {
                genres.add(it.name)
            }
            tvGenresValue.text = getString(
                R.string.value_with_colon,
                genres.joinToString(
                    separator = ", "
                )
            )

            val themes = ArrayList<String>()
            manga.themes.map {
                themes.add(it.name)
            }
            tvThemeValue.text = getString(
                R.string.value_with_colon,
                themes.joinToString(
                    separator = ", "
                )
            )

            val authors = ArrayList<String>()
            manga.authors.map {
                authors.add(it.name)
            }
            tvAuthorsValue.text = getString(
                R.string.value_with_colon,
                authors.joinToString(
                    separator = ", "
                )
            )

            tvSynopsisValue.text = manga.synopsis ?: getString(R.string.label_unknown)

            var isFavorite = manga.isFavorite
            setIconFavorite(isFavorite)
            binding.fabFavorite.setOnClickListener {
                isFavorite = !isFavorite
                setIconFavorite(isFavorite)
                if (isFavorite) {
                    detailMangaViewModel.insertFavoriteManga(manga)
                    Toast.makeText(
                        this@DetailMangaActivity,
                        getString(R.string.add_manga_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    detailMangaViewModel.deleteFavoriteManga(manga.malId)
                    Toast.makeText(
                        this@DetailMangaActivity,
                        getString(R.string.delete_manga_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
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