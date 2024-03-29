package com.secbreel.notes.ui.screens.create_note

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentCreateNotesBinding
import com.secbreel.notes.ui.screens.ApplicationActivity.Companion.toolbarTitle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateNotesFragment() : Fragment(R.layout.fragment_create_notes) {

    private val viewModel by viewModel<CreateNotesViewModel>()
    private var categoryId: Int = 0
    private val viewBinding by viewBinding(FragmentCreateNotesBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryId = arguments?.getInt("arg1")!!
        viewBinding.categoryName.text = arguments?.toolbarTitle

        viewBinding.saveNote.setOnClickListener {

            val title: String = viewBinding.noteTitle.text.toString()
            val text: String = viewBinding.noteText.text.toString()

            viewModel.saveNote(title, text, categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    viewModel.returnToPreviousScreen()
                }
                .subscribe()
        }
    }

}