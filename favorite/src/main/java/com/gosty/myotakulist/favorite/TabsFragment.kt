package com.gosty.myotakulist.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.gosty.myotakulist.anime.detail.DetailAnimeActivity
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.core.ui.AnimePagingAdapter
import com.gosty.myotakulist.core.ui.LoadingStateAdapter
import com.gosty.myotakulist.core.ui.MangaPagingAdapter
import com.gosty.myotakulist.di.FavoriteModuleDependencies
import com.gosty.myotakulist.favorite.databinding.FragmentTabsBinding
import com.gosty.myotakulist.manga.detail.DetailMangaActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class TabsFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory

    private val tabsViewModel: TabsViewModel by viewModels {
        factory
    }

    private var _binding: FragmentTabsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            DaggerFavoriteComponent
                .builder()
                .context(requireContext())
                .appDependencies(
                    EntryPointAccessors.fromApplication(
                        requireActivity().applicationContext,
                        FavoriteModuleDependencies::class.java
                    )
                )
                .build()
                .inject(this)

            val tabName = arguments?.getString(ARG_TAB)
            val layoutManager = LinearLayoutManager(requireContext())
            binding?.rvFavorite?.layoutManager = layoutManager
            binding?.rvFavorite?.setHasFixedSize(true)

            if (tabName == TAB_ANIME) {
                val animePagingAdapter = AnimePagingAdapter()

                binding?.rvFavorite?.adapter = animePagingAdapter.withLoadStateFooter(
                    footer = LoadingStateAdapter {
                        animePagingAdapter.retry()
                    }
                )

                binding?.btnRetry?.setOnClickListener {
                    animePagingAdapter.refresh()
                }

                animePagingAdapter
                    .loadStateFlow
                    .asLiveData()
                    .observe(viewLifecycleOwner) {
                        it.refresh.apply {
                            when (this) {
                                is LoadState.Loading -> {
                                    binding?.apply {
                                        pbProgressBar.visibility = View.VISIBLE
                                        tvLoading.visibility = View.VISIBLE
                                        rvFavorite.visibility = View.GONE
                                        tvError.visibility = View.GONE
                                        btnRetry.visibility = View.GONE
                                    }
                                }

                                is LoadState.NotLoading -> {
                                    binding?.apply {
                                        pbProgressBar.visibility = View.GONE
                                        tvLoading.visibility = View.GONE
                                        rvFavorite.visibility = View.VISIBLE
                                        tvError.visibility = View.GONE
                                        btnRetry.visibility = View.GONE
                                    }

                                    if (animePagingAdapter.itemCount == 0) {
                                        Toast.makeText(
                                            requireContext(),
                                            getString(com.gosty.myotakulist.R.string.empty_favorite_anime),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                is LoadState.Error -> {
                                    binding?.apply {
                                        pbProgressBar.visibility = View.GONE
                                        tvLoading.visibility = View.GONE
                                        rvFavorite.visibility = View.GONE
                                        tvError.visibility = View.VISIBLE
                                        btnRetry.visibility = View.VISIBLE
                                    }

                                    Toast.makeText(
                                        requireContext(),
                                        error.localizedMessage,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }

                tabsViewModel.getAllFavoriteAnime().observe(viewLifecycleOwner) {
                    animePagingAdapter.submitData(lifecycle, it)
                }

                animePagingAdapter.setOnItemClickCallback(object :
                    AnimePagingAdapter.OnItemClickCallback {
                    override fun onItemClicked(anime: Anime) {
                        val intent = Intent(activity, DetailAnimeActivity::class.java)
                        intent.putExtra(DetailAnimeActivity.EXTRA_DATA, anime)
                        startActivity(intent)
                    }
                })
            } else {
                val mangaPagingAdapter = MangaPagingAdapter()
                binding?.rvFavorite?.adapter = mangaPagingAdapter.withLoadStateFooter(
                    footer = LoadingStateAdapter {
                        mangaPagingAdapter.retry()
                    }
                )

                binding?.btnRetry?.setOnClickListener {
                    mangaPagingAdapter.refresh()
                }

                mangaPagingAdapter
                    .loadStateFlow
                    .asLiveData()
                    .observe(viewLifecycleOwner) {
                        it.refresh.apply {
                            when (this) {
                                is LoadState.Loading -> {
                                    binding?.apply {
                                        pbProgressBar.visibility = View.VISIBLE
                                        tvLoading.visibility = View.VISIBLE
                                        rvFavorite.visibility = View.GONE
                                        tvError.visibility = View.GONE
                                        btnRetry.visibility = View.GONE
                                    }
                                }

                                is LoadState.NotLoading -> {
                                    binding?.apply {
                                        pbProgressBar.visibility = View.GONE
                                        tvLoading.visibility = View.GONE
                                        rvFavorite.visibility = View.VISIBLE
                                        tvError.visibility = View.GONE
                                        btnRetry.visibility = View.GONE
                                    }
                                    if (mangaPagingAdapter.itemCount == 0) {
                                        Toast.makeText(
                                            requireContext(),
                                            getString(com.gosty.myotakulist.R.string.empty_favorite_manga),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                is LoadState.Error -> {
                                    binding?.apply {
                                        pbProgressBar.visibility = View.GONE
                                        tvLoading.visibility = View.GONE
                                        rvFavorite.visibility = View.GONE
                                        tvError.visibility = View.VISIBLE
                                        btnRetry.visibility = View.VISIBLE
                                    }

                                    Toast.makeText(
                                        requireContext(),
                                        error.localizedMessage,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }

                tabsViewModel.getAllFavoriteManga().observe(viewLifecycleOwner) {
                    mangaPagingAdapter.submitData(lifecycle, it)
                }

                mangaPagingAdapter.setOnItemClickCallback(object :
                    MangaPagingAdapter.OnItemClickCallback {
                    override fun onItemClicked(manga: Manga) {
                        val intent = Intent(activity, DetailMangaActivity::class.java)
                        intent.putExtra(DetailMangaActivity.EXTRA_DATA, manga)
                        startActivity(intent)
                    }
                })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARG_TAB = "tab"
        const val TAB_ANIME = "anime"
        const val TAB_MANGA = "manga"
    }
}