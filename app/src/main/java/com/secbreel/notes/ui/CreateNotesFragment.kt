package com.secbreel.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.secbreel.notes.R
import com.secbreel.notes.model.Category
import com.secbreel.notes.model.Note
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
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
                    Navigation.findNavController(
                        activity as AppCompatActivity,
                        R.id.nav_host_fragment
                    ).popBackStack()
                }
                .subscribe()
        }

        return rootView
    }

}