package com.secbreel.notes.ui.calendar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.secbreel.notes.R
import com.secbreel.notes.databinding.FragmentCalendarBinding
import com.secbreel.notes.ui.category_screen.NotesAdapter
import com.secbreel.notes.ui.note_screen.NoteScreenFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    private val viewBinding by viewBinding(FragmentCalendarBinding::bind)
    private val viewModel by viewModel<CalendarViewModel>()
    private lateinit var disposable: Disposable
    private val router by inject<Router>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = viewBinding.notesList
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        disposable = viewModel.getNotes()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { items ->
                recyclerView.adapter = NotesAdapter(items) { note ->
                    router.navigateTo(FragmentScreen
                    {
                        val bundle = Bundle()
                        bundle.putInt("arg1", note.id!!)
                        NoteScreenFragment().apply {
                            arguments = bundle
                        }
                    })
                }
            }
            .subscribe()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }

}