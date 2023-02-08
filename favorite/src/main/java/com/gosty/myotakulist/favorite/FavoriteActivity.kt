package com.gosty.myotakulist.favorite

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.gosty.myotakulist.favorite.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(com.gosty.myotakulist.R.string.title_favorite)
        supportActionBar?.elevation = 0f

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tabs, position ->
            tabs.text = getString(TAB_TITLES[position])
        }.attach()
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            com.gosty.myotakulist.R.string.anime,
            com.gosty.myotakulist.R.string.manga
        )
    }
}