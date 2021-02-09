package com.secbreel.notes.ui

import android.app.ActivityManager
import android.content.Context.ACTIVITY_SERVICE
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.secbreel.notes.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File


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

        rootView.findViewById<RadioGroup>(R.id.themeChangingRadioGroup).setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.systemThemeSettingsButton -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
                R.id.lightThemeSettingsButton -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                R.id.darkThemeSettingsButton -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        }


        return rootView
    }

}