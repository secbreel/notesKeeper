package com.secbreel.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.secbreel.notes.R
import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject


class SettingsFragment : Fragment() {
    val repository by inject<CategoryRepository>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)

        rootView.findViewById<Button>(R.id.clearCategories).setOnClickListener {
            repository.clear()
                .subscribeOn(Schedulers.io())
                .subscribe()
            Toast.makeText(requireContext(), "CATEGORIES CLEARED!", Toast.LENGTH_SHORT).show()
        }


        return rootView
    }

}