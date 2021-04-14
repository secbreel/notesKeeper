package com.secbreel.notes.ui.calendar

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
import com.secbreel.notes.databinding.FragmentCalendarBinding
import com.secbreel.notes.ui.category_screen.NotesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel


class CalendarFragment : Fragment() {

    private lateinit var viewBinding: FragmentCalendarBinding
    private val viewModel by viewModel<CalendarViewModel>()
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCalendarBinding.inflate(layoutInflater, container, false)
        val recyclerView = viewBinding.notesList
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        disposable = viewModel.getNotes()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { items ->
                recyclerView.adapter = NotesAdapter(items) {
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