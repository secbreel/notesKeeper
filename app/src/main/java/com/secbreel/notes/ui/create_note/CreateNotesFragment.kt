package com.secbreel.notes.ui.create_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.secbreel.notes.databinding.FragmentCreateNotesBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateNotesFragment() : Fragment() {

    private val viewModel by viewModel<CreateNotesViewModel>()
    private var categoryId: Int = 0
    private lateinit var categoryTitle: String
    private lateinit var viewBinding: FragmentCreateNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        val rootView = viewBinding.root
        categoryId = arguments?.getInt("arg1")!!
        categoryTitle = arguments?.getString("arg2")!!
        viewBinding.categoryName.text = categoryTitle
        viewBinding.saveNote.setOnClickListener {
            val title: String = viewBinding.noteTitle.text.toString()
            val text: String = viewBinding.noteText.text.toString()
            viewModel.saveNote(title, text, categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    findNavController().navigateUp()
                }
                .subscribe()
        }

        return rootView
    }

}