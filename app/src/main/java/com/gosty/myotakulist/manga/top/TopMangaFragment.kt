package com.gosty.myotakulist.manga.top

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
import com.gosty.myotakulist.core.domain.model.manga.Manga
import com.gosty.myotakulist.core.ui.LoadingStateAdapter
import com.gosty.myotakulist.core.ui.MangaPagingAdapter
import com.gosty.myotakulist.databinding.FragmentTopMangaBinding
import com.gosty.myotakulist.manga.detail.DetailMangaActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopMangaFragment : Fragment() {
    private val topMangaViewModel: TopMangaViewModel by viewModels()
    private var _binding: FragmentTopMangaBinding? = null
    private val binding get() = _binding
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
        _binding = FragmentTopMangaBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val layoutManager = LinearLayoutManager(requireContext())
            binding?.rvTopManga?.apply {
                this.layoutManager = layoutManager
                setHasFixedSize(true)
                adapter = mangaPagingAdapter.withLoadStateFooter(
                    footer = LoadingStateAdapter {
                        mangaPagingAdapter.retry()
                    }
                )
            }

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
                                    rvTopManga.visibility = View.GONE
                                    tvError.visibility = View.GONE
                                    btnRetry.visibility = View.GONE
                                }
                            }

                            is LoadState.NotLoading -> {
                                binding?.apply {
                                    pbProgressBar.visibility = View.GONE
                                    tvLoading.visibility = View.GONE
                                    rvTopManga.visibility = View.VISIBLE
                                    tvError.visibility = View.GONE
                                    btnRetry.visibility = View.GONE
                                }
                            }

                            is LoadState.Error -> {
                                binding?.apply {
                                    pbProgressBar.visibility = View.GONE
                                    tvLoading.visibility = View.GONE
                                    rvTopManga.visibility = View.GONE
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

            topMangaViewModel.getTopManga().observe(viewLifecycleOwner) {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                findNavController().navigate(TopMangaFragmentDirections.topMangaToSetting())
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}