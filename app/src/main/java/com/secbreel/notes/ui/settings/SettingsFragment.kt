package com.secbreel.notes.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel by viewModel<SettingsFragmentViewModel>()
    private val viewBinding by viewBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.clearCategories.setOnClickListener {
            viewModel.clearCategories()
            Toast.makeText(requireContext(), "CATEGORIES CLEARED!", Toast.LENGTH_SHORT).show()
        }
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                viewBinding.lightThemeSettingsButton.isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                viewBinding.darkThemeSettingsButton.isChecked = true
            }
            else -> {
                viewBinding.systemThemeSettingsButton.isChecked = true
            }
        }

        viewBinding.themeChangingRadioGroup
            .setOnCheckedChangeListener { group, checkedId ->
                var theme : Int = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                when (checkedId) {
                    R.id.systemThemeSettingsButton -> {
                        theme = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    }
                    R.id.lightThemeSettingsButton -> {
                        theme = AppCompatDelegate.MODE_NIGHT_NO
                    }
                    R.id.darkThemeSettingsButton -> {
                        theme = AppCompatDelegate.MODE_NIGHT_YES
                    }
                }
                viewModel.saveThemeMode(theme)
                AppCompatDelegate.setDefaultNightMode(theme)
            }

    }

}