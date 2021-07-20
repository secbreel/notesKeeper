package com.secbreel.notes.ui.screens.calendar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentCalendarBinding
import com.secbreel.notes.ui.ext.subscribe
import com.secbreel.notes.ui.screens.category_screen.NotesAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel


class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    private val viewBinding by viewBinding(FragmentCalendarBinding::bind)
    private val viewModel by viewModel<CalendarViewModel>()
    private val onNoteClicks: PublishSubject<Bundle> = PublishSubject.create()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setup()

        val recyclerView = viewBinding.notesList
        recyclerView.layoutManager = LinearLayoutManager(this.context)


        viewModel.getNotes()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { items ->
                recyclerView.adapter = NotesAdapter(items) { note ->
                    val bundle = Bundle()
                    bundle.putInt("arg1", note.id!!)
                    onNoteClicks.onNext(bundle)
                }
            }
            .subscribe(viewLifecycleOwner)

    }

    private fun BaseCalendarViewModel.setup() =
        attach(object : BaseCalendarViewModel.Input {
            override val onNoteClicked: Observable<Bundle> = onNoteClicks
        }).subscribe(viewLifecycleOwner)




}