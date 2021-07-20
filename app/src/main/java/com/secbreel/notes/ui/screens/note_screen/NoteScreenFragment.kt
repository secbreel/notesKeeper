package com.secbreel.notes.ui.screens.note_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentNoteScreenBinding
import com.secbreel.notes.ui.ext.subscribe
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteScreenFragment : Fragment(R.layout.fragment_note_screen) {

    private val viewBinding by viewBinding(FragmentNoteScreenBinding::bind)
    private val viewModel by viewModel<NoteScreenViewModel>()
    private var noteId: Int = 0
    lateinit var disposable: Disposable


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNote(noteId).observeOn(AndroidSchedulers.mainThread())
            .doOnNext { note ->
                viewBinding.noteTitle.setText(note.title)
                viewBinding.noteText.setText(note.text)
                viewBinding.updateDate.text = note.date
                viewBinding.categoryName.text = note.categoryName

            }
            .subscribe(viewLifecycleOwner)
    }
}