package com.secbreel.notes.ui.create_note

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.secbreel.notes.R
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class CreateNotesFragment() : Fragment() {

    private val viewModel by viewModel<CreateNotesViewModel>()
    private var categoryId: Int = 0
    private lateinit var categoryTitle: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_create_notes, container, false)
        categoryId = arguments?.getInt("arg1")!!
        categoryTitle = arguments?.getString("arg2")!!
        rootView.findViewById<TextView>(R.id.categoryName).text = categoryTitle
        rootView.findViewById<FloatingActionButton>(R.id.saveNote).setOnClickListener {
            val title: String = view?.findViewById<EditText>(R.id.noteTitle)?.text.toString()
            val text: String = view?.findViewById<EditText>(R.id.noteText)?.text.toString()
            viewModel.saveNote(title, text, categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    findNavController().navigateUp()
                }
                .doOnError{
                    Log.e("MYTAG", "error", it)
                }
                .subscribe()
        }

        return rootView
    }

}