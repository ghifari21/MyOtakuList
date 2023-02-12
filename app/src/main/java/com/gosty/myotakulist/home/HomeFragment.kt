package com.gosty.myotakulist.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.gosty.myotakulist.R
import com.gosty.myotakulist.anime.detail.DetailAnimeActivity
import com.gosty.myotakulist.core.domain.model.anime.Anime
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.core.ui.AnimePagingAdapter
import com.gosty.myotakulist.core.ui.LoadingStateAdapter
import com.gosty.myotakulist.core.ui.MangaPagingAdapter
import com.gosty.myotakulist.databinding.FragmentHomeBinding
import com.gosty.myotakulist.manga.detail.DetailMangaActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val animePagingAdapter = AnimePagingAdapter()
    private val mangaPagingAdapter = MangaPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val layoutManager = LinearLayoutManager(requireContext())
            binding?.rvItemList?.layoutManager = layoutManager
            binding?.rvItemList?.setHasFixedSize(true)

            setData()

            animePagingAdapter.setOnItemClickCallback(object :
                AnimePagingAdapter.OnItemClickCallback {
                override fun onItemClicked(anime: Anime) {
                    val intent = Intent(activity, DetailAnimeActivity::class.java)
                    intent.putExtra(DetailAnimeActivity.EXTRA_DATA, anime)
                    startActivity(intent)
                }
            })

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
                                    rvItemList.visibility = View.GONE
                                    tvError.visibility = View.GONE
                                    btnRetry.visibility = View.GONE
                                }
                            }

                            is LoadState.NotLoading -> {
                                binding?.apply {
                                    pbProgressBar.visibility = View.GONE
                                    tvLoading.visibility = View.GONE
                                    rvItemList.visibility = View.VISIBLE
                                    tvError.visibility = View.GONE
                                    btnRetry.visibility = View.GONE
                                }
                                if (animePagingAdapter.itemCount == 0) {
                                    Toast.makeText(
                                        requireContext(),
                                        getString(R.string.not_found),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            is LoadState.Error -> {
                                binding?.apply {
                                    pbProgressBar.visibility = View.GONE
                                    tvLoading.visibility = View.GONE
                                    rvItemList.visibility = View.GONE
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

            mangaPagingAdapter.setOnItemClickCallback(object :
                MangaPagingAdapter.OnItemClickCallback {
                override fun onItemClicked(manga: Manga) {
                    val intent = Intent(activity, DetailMangaActivity::class.java)
                    intent.putExtra(DetailMangaActivity.EXTRA_DATA, manga)
                    startActivity(intent)
                }
            })

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
                                    rvItemList.visibility = View.GONE
                                    tvError.visibility = View.GONE
                                    btnRetry.visibility = View.GONE
                                }
                            }

                            is LoadState.NotLoading -> {
                                binding?.apply {
                                    pbProgressBar.visibility = View.GONE
                                    tvLoading.visibility = View.GONE
                                    rvItemList.visibility = View.VISIBLE
                                    tvError.visibility = View.GONE
                                    btnRetry.visibility = View.GONE
                                }
                                if (mangaPagingAdapter.itemCount == 0) {
                                    Toast.makeText(
                                        requireContext(),
                                        getString(R.string.not_found),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            is LoadState.Error -> {
                                binding?.apply {
                                    pbProgressBar.visibility = View.GONE
                                    tvLoading.visibility = View.GONE
                                    rvItemList.visibility = View.GONE
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

            binding?.btnRetry?.setOnClickListener {
                if (binding?.swSearchFilter?.isChecked == true) {
                    mangaPagingAdapter.refresh()
                } else {
                    animePagingAdapter.refresh()
                }
            }

        }
    }

    private fun setData() {
        binding?.svSearch?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding?.tvWelcome?.visibility = View.GONE
                binding?.swSearchFilter?.isChecked
                if (binding?.swSearchFilter?.isChecked == true) {
                    binding?.rvItemList?.adapter = mangaPagingAdapter.withLoadStateFooter(
                        footer = LoadingStateAdapter {
                            mangaPagingAdapter.retry()
                        }
                    )

                    if (query != null) {
                        homeViewModel.getSearchManga(query).observe(viewLifecycleOwner) {
                            mangaPagingAdapter.submitData(lifecycle, it)
                        }
                    }
                } else {
                    binding?.rvItemList?.adapter = animePagingAdapter.withLoadStateFooter(
                        footer = LoadingStateAdapter {
                            animePagingAdapter.retry()
                        }
                    )

                    if (query != null) {
                        homeViewModel.getSearchAnime(query).observe(viewLifecycleOwner) {
                            animePagingAdapter.submitData(lifecycle, it)
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                findNavController().navigate(HomeFragmentDirections.homeToSetting())
                true
            }
            R.id.action_favorite -> {
                startActivity(
                    Intent(
                        requireContext(),
                        Class.forName("com.gosty.myotakulist.favorite.FavoriteActivity")
                    )
                )
                true
            }
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}