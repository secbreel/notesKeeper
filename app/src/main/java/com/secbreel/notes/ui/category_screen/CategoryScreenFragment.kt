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
    private lateinit var viewBinding: FragmentCategoryScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCategoryScreenBinding.inflate(layoutInflater, container, false)
        val recyclerView = viewBinding.notesList
        categoryTitle = arguments?.getString("arg2")!!
        categoryId = arguments?.getInt("arg1")!!
        (activity as AppCompatActivity?)!!.supportActionBar?.title = categoryTitle
        recyclerView.layoutManager = LinearLayoutManager(this.context)
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
                recyclerView.adapter = NotesAdapter(items) {
                    val bundle = Bundle()
                    bundle.putInt("arg1", it.id!!)
                    navigationController.navigate(
                        R.id.action_categoryScreenFragment3_to_noteScreen,
                        bundle
                    )
                }
            }
            .subscribe()
        return viewBinding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }
}