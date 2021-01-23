package com.secbreel.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.secbreel.notes.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment() {

    private val viewModel by viewModel<SettingsFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        GlideApp
            .with(this)
            .load("")
            .error(R.drawable.ic_baseline_settings_24)
            .centerCrop()
            .into(rootView.findViewById(R.id.settingsIcon))
        rootView.findViewById<Button>(R.id.clearCategories).setOnClickListener {
            viewModel.categoryRepositoryClear
                .subscribe()
            viewModel.notesRepositoryClear
                .subscribe()
            Toast.makeText(requireContext(), "CATEGORIES CLEARED!", Toast.LENGTH_SHORT).show()
        }


        return rootView
    }

}