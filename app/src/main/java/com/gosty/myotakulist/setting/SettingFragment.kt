package com.gosty.myotakulist.setting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.gosty.myotakulist.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val sharedPref = requireContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE)
            binding.swDarkMode.isChecked = sharedPref.getBoolean(THEME, false)
            binding.swDarkMode.setOnCheckedChangeListener { view: CompoundButton, isChecked: Boolean ->
                sharedPref.edit().putBoolean(THEME, isChecked).apply()
                view.isChecked = isChecked

                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val SETTING = "setting"
        const val THEME = "theme"
    }
}