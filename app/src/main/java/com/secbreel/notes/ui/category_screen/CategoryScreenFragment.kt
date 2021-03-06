package com.secbreel.notes.ui.category_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentCategoryScreenBinding
import com.secbreel.notes.databinding.ItemNoteBinding
import com.secbreel.notes.databinding.ItemNoteDateBinding
import com.secbreel.notes.model.DateItem
import com.secbreel.notes.model.ListItem
import com.secbreel.notes.model.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryScreenFragment() : Fragment() {

    private var categoryId: Int = 0
    private lateinit var categoryTitle: String
    private val viewModel by viewModel<CategoryScreenViewModel>()
    var disposable: Disposable = Disposables.disposed()
    private lateinit var navigationController: NavController
    private lateinit var viewBinding : FragmentCategoryScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCategoryScreenBinding.inflate(layoutInflater, container, false)
        val rootView = viewBinding.root
        val recyclerView = viewBinding.notesList
        categoryTitle = arguments?.getString("arg2")!!
        categoryId = arguments?.getInt("arg1")!!
        (activity as AppCompatActivity?)!!.supportActionBar?.title = categoryTitle
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        navigationController =
            Navigation.findNavController(activity as AppCompatActivity, R.id.nav_host_fragment)
        viewBinding.addNote.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("arg1", categoryId)
            bundle.putString("arg2", categoryTitle)
            navigationController.navigate(
                R.id.action_categoryScreenFragment3_to_createNotesFragment3,
                bundle
            )
        }

        disposable = viewModel.getNotes(categoryId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { items ->
                recyclerView.adapter =
                    NotesAdapter(items) { view, item ->
                        when (item.getType()) {
                            ListItem.TYPE_DATE -> {
                                val dateItemBinding = ItemNoteDateBinding.bind(view)
                                val dateItem: DateItem = item as DateItem
                                dateItemBinding.notesDate.text = dateItem.date
                            }
                            ListItem.TYPE_NOTE -> {
                                val noteItemBinding = ItemNoteBinding.bind(view)
                                val note: Note = item as Note
                                noteItemBinding.noteName.text = note.title
                                noteItemBinding.noteCreationDate.text =
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