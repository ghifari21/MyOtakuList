package com.gosty.myotakulist.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gosty.myotakulist.favorite.TabsFragment.Companion.ARG_TAB
import com.gosty.myotakulist.favorite.TabsFragment.Companion.TAB_ANIME
import com.gosty.myotakulist.favorite.TabsFragment.Companion.TAB_MANGA

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        val fragment = TabsFragment()
        val bundle = Bundle()
        if (position == 0) {
            bundle.putString(ARG_TAB, TAB_ANIME)
        } else {
            bundle.putString(ARG_TAB, TAB_MANGA)
        }
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemCount(): Int = 2
}