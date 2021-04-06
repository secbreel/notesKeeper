package com.secbreel.notes.ui.note_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentNoteScreenBinding

class NoteScreen : Fragment() {

    private lateinit var viewBinding: FragmentNoteScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNoteScreenBinding.inflate(inflater, container, false)


        return viewBinding.root
    }
}