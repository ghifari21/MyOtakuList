package com.gosty.myotakulist.anime.season

import android.content.Intent
import android.os.Bundle
import android.view.*
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
import com.gosty.myotakulist.core.ui.AnimePagingAdapter
import com.gosty.myotakulist.core.ui.LoadingStateAdapter
import com.gosty.myotakulist.databinding.FragmentSeasonAnimeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeasonAnimeFragment : Fragment() {
    private val seasonAnimeViewModel: SeasonAnimeViewModel by viewModels()
    private var _binding: FragmentSeasonAnimeBinding? = null
    private val binding get() = _binding!!
    private val animePagingAdapter = AnimePagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeasonAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val layoutManager = LinearLayoutManager(requireContext())
            binding.rvNowPlayingAnime.apply {
                this.layoutManager = layoutManager
                setHasFixedSize(true)
                adapter = animePagingAdapter.withLoadStateFooter(
                    footer = LoadingStateAdapter {
                        animePagingAdapter.retry()
                    }
                )
            }

            binding.btnRetry.setOnClickListener {
                animePagingAdapter.refresh()
            }

            animePagingAdapter
                .loadStateFlow
                .asLiveData()
                .observe(viewLifecycleOwner) {
                    it.refresh.apply {
                        when (this) {
                            is LoadState.Loading -> {
                                binding.apply {
                                    pbProgressBar.visibility = View.VISIBLE
                                    tvLoading.visibility = View.VISIBLE
                                    rvNowPlayingAnime.visibility = View.GONE
                                    tvError.visibility = View.GONE
                                    btnRetry.visibility = View.GONE
                                }
                            }

                            is LoadState.NotLoading -> {
                                binding.apply {
                                    pbProgressBar.visibility = View.GONE
                                    tvLoading.visibility = View.GONE
                                    rvNowPlayingAnime.visibility = View.VISIBLE
                                    tvError.visibility = View.GONE
                                    btnRetry.visibility = View.GONE
                                }
                            }

                            is LoadState.Error -> {
                                binding.apply {
                                    pbProgressBar.visibility = View.GONE
                                    tvLoading.visibility = View.GONE
                                    rvNowPlayingAnime.visibility = View.GONE
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

            seasonAnimeViewModel.getNowPlayingAnime().observe(viewLifecycleOwner) {
                animePagingAdapter.submitData(lifecycle, it)
            }

            animePagingAdapter.setOnItemClickCallback(object : AnimePagingAdapter.OnItemClickCallback {
                override fun onItemClicked(anime: Anime) {
                    val intent = Intent(activity, DetailAnimeActivity::class.java)
                    intent.putExtra(DetailAnimeActivity.EXTRA_DATA, anime)
                    startActivity(intent)
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                findNavController().navigate(SeasonAnimeFragmentDirections.nowPlayingAnimeToSetting())
                true
            }
            R.id.action_favorite -> {
                startActivity(Intent(requireContext(), Class.forName("com.gosty.myotakulist.favorite.FavoriteActivity")))
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}