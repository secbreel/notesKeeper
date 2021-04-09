package com.secbreel.notes.ui.note_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentNoteScreenBinding
import com.secbreel.notes.ui.category_screen.CategoryScreenViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteScreenFragment : Fragment() {

    private lateinit var viewBinding: FragmentNoteScreenBinding
    private val viewModel by viewModel<NoteScreenViewModel>()
    private var noteId: Int = 0
    lateinit var disposable: Disposable


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNoteScreenBinding.inflate(inflater, container, false)
        noteId = arguments?.getInt("arg1")!!
        viewBinding.saveNote.setOnClickListener {
            viewModel.updateNote(
                noteId,
                viewBinding.noteTitle.text.toString(),
                viewBinding.noteText.text.toString()
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }

        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        disposable = viewModel.getNote(noteId).observeOn(AndroidSchedulers.mainThread())
            .doOnNext { note ->
                //todo make changing category name
                viewBinding.noteTitle.setText(note.title)
                viewBinding.noteText.setText(note.text)
                viewBinding.updateDate.text = note.date
                viewBinding.categoryName.text = note.categoryName
                (activity as AppCompatActivity?)!!.supportActionBar?.title = note.categoryName

            }
            .subscribe()
    }

    override fun onPause() {
        super.onPause()
        disposable.dispose()
    }
}