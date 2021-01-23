package com.secbreel.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.request.target.Target
import com.secbreel.notes.R


class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_calendar, container, false)
        GlideApp
            .with(this)
            .load(R.drawable.anime_tyan)
            .override(Target.SIZE_ORIGINAL)
            .centerCrop()
            .into(rootView.findViewById(R.id.calendarTestImage))


        return rootView
    }
}