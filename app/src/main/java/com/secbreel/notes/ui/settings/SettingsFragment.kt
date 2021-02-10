package com.secbreel.notes.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.secbreel.notes.R
import com.secbreel.notes.ui.settings.SettingsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment() {

    private val viewModel by viewModel<SettingsFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)

        rootView.findViewById<Button>(R.id.clearCategories).setOnClickListener {
            viewModel.categoryRepositoryClear
                .subscribe()
            viewModel.notesRepositoryClear
                .subscribe()
            Toast.makeText(requireContext(), "CATEGORIES CLEARED!", Toast.LENGTH_SHORT).show()
        }
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
                rootView.findViewById<RadioButton>(R.id.systemThemeSettingsButton).isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_NO -> {
                rootView.findViewById<RadioButton>(R.id.lightThemeSettingsButton).isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                rootView.findViewById<RadioButton>(R.id.darkThemeSettingsButton).isChecked = true
            }
        }

        rootView.findViewById<RadioGroup>(R.id.themeChangingRadioGroup)
            .setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.systemThemeSettingsButton -> {
                        viewModel.saveThemeMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                    R.id.lightThemeSettingsButton -> {
                        viewModel.saveThemeMode(AppCompatDelegate.MODE_NIGHT_NO)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    R.id.darkThemeSettingsButton -> {
                        viewModel.saveThemeMode(AppCompatDelegate.MODE_NIGHT_YES)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                }
            }


        return rootView
    }

}