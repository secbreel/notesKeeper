package com.secbreel.notes.ui.screens.category_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.view.clicks
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentCategoryScreenBinding
import com.secbreel.notes.ui.ext.subscribe
import com.secbreel.notes.ui.screens.ApplicationActivity.Companion.toolbarTitle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryScreenFragment() : Fragment(R.layout.fragment_category_screen) {

    private var categoryId: Int = 0
    private val viewModel by viewModel<BaseCategoryScreenViewModel>()
    private val viewBinding by viewBinding(FragmentCategoryScreenBinding::bind)
    private val onNoteClicks: PublishSubject<Bundle> = PublishSubject.create()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setup()
        categoryId = arguments?.getInt("arg1")!!

        val recyclerView = viewBinding.notesList
        recyclerView.layoutManager = LinearLayoutManager(this.context)


        viewModel.getNotes(categoryId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { items ->
                recyclerView.adapter = NotesAdapter(items) {
                    val bundle = Bundle()
                    bundle.putInt("arg1", it.id!!)
                    bundle.toolbarTitle = arguments?.toolbarTitle
                    onNoteClicks.onNext(bundle)
                }
            }
            .subscribe(viewLifecycleOwner)

    }

    private fun BaseCategoryScreenViewModel.setup() {
        attach(object : BaseCategoryScreenViewModel.Input {
            override val onCreateNoteClicked: Observable<Bundle> =
                viewBinding.addNote.clicks().flatMap {
                    val bundle = Bundle()
                    bundle.putInt("arg1", categoryId)
                    bundle.toolbarTitle = arguments?.toolbarTitle
                    Observable.just(bundle)
                }
            override val onNoteClicked: Observable<Bundle> = onNoteClicks
        }).subscribe(viewLifecycleOwner)
    }
}