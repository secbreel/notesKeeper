package com.secbreel.notes.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.secbreel.notes.databinding.FragmentCalendarBinding


class CalendarFragment : Fragment() {

    private lateinit var viewBinding: FragmentCalendarBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCalendarBinding.inflate(layoutInflater, container, false)
        val rootView = viewBinding.root

        return rootView
    }
}