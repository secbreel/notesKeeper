package com.secbreel.notes.ui.category_screen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentCategoryScreenBinding
import com.secbreel.notes.ui.create_note.CreateNotesFragment
import com.secbreel.notes.ui.note_screen.NoteScreenFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryScreenFragment() : Fragment(R.layout.fragment_category_screen) {

    private var categoryId: Int = 0
    private lateinit var categoryTitle: String
    private val viewModel by viewModel<CategoryScreenViewModel>()
    var disposable: Disposable = Disposables.disposed()
    private val viewBinding by viewBinding(FragmentCategoryScreenBinding::bind)
    private val router by inject<Router>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = viewBinding.notesList
        categoryTitle = arguments?.getString("arg2")!!
        categoryId = arguments?.getInt("arg1")!!
        (activity as AppCompatActivity?)!!.supportActionBar?.title = categoryTitle
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        /*navigationController =
            Navigation.findNavController(activity as AppCompatActivity, R.id.nav_host_fragment)*/
        viewBinding.addNote.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("arg1", categoryId)
            bundle.putString("arg2", categoryTitle)
            router.navigateTo(FragmentScreen {
                CreateNotesFragment().apply {
                    arguments = bundle
                }
            })
        }
        disposable = viewModel.getNotes(categoryId)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnNext { items ->
                recyclerView.adapter = NotesAdapter(items) {
                    val bundle = Bundle()
                    bundle.putInt("arg1", it.id!!)
                    router.navigateTo(FragmentScreen {
                        NoteScreenFragment().apply {
                            arguments = bundle
                        }
                    })
                }
            }
            ?.subscribe()!!
        
    }


    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }
}