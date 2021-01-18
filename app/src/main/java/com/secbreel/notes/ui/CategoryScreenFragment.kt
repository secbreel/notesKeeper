package com.secbreel.notes.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.secbreel.notes.R
import com.secbreel.notes.model.Category
import com.secbreel.notes.model.DateItem
import com.secbreel.notes.model.Note
import com.secbreel.notes.persistance.NoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_create_notes.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CategoryScreenFragment(val category: Category) : Fragment() {

    //private val repository by inject<NoteRepository>()
    private val viewModel by viewModel<CategoryScreenViewModel>()
    var disposable: Disposable = Disposables.disposed()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_category_screen, container, false)
        //viewModel.categoryId = category.id
        (activity as AppCompatActivity?)!!.supportActionBar?.title = category.title
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.notesList)
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager

        rootView.findViewById<FloatingActionButton>(R.id.addNote).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, CreateNotesFragment(category), null)
                .addToBackStack(null).commit()
        }

        disposable = viewModel.getNotes(category.id!!)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { items ->
                recyclerView.adapter =
                    NotesAdapter(items) { view, item ->
                        when (item.getType()) {
                            ListItem.TYPE_DATE -> {
                                val dateItem: DateItem = item as DateItem
                                view.findViewById<TextView>(R.id.notesDate).text = dateItem.date
                            }
                            ListItem.TYPE_NOTE -> {
                                val note: Note = item as Note

                                    view.findViewById<TextView>(R.id.noteName).text = note.title
                                    view.findViewById<TextView>(R.id.noteCreationDate).text =
                                        note.date
                            }
                        }
                    }
            }
            .subscribe()

        return rootView
    }


    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }

}