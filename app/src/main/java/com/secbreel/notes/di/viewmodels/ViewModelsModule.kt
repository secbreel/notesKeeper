package com.secbreel.notes.di.viewmodels

import com.secbreel.notes.ui.screens.ApplicationActivityViewModel
import com.secbreel.notes.ui.screens.BaseApplicationActivityViewModel
import com.secbreel.notes.ui.screens.calendar.CalendarViewModel
import com.secbreel.notes.ui.screens.categories_list.BaseCategoriesListViewModel
import com.secbreel.notes.ui.screens.categories_list.CategoriesListViewModel
import com.secbreel.notes.ui.screens.category_screen.BaseCategoryScreenViewModel
import com.secbreel.notes.ui.screens.category_screen.CategoryScreenViewModel
import com.secbreel.notes.ui.screens.create_category.CreateCategoryViewModel
import com.secbreel.notes.ui.screens.create_note.CreateNotesViewModel
import com.secbreel.notes.ui.screens.note_screen.NoteScreenViewModel
import com.secbreel.notes.ui.screens.settings.SettingsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {

    viewModel<BaseApplicationActivityViewModel> {
        ApplicationActivityViewModel(router = get())
    }

    viewModel<BaseCategoriesListViewModel> {
        CategoriesListViewModel(getCategoriesUseCase = get(), router = get())
    }
    viewModel {
        SettingsFragmentViewModel(clearData = get(), savePreferences = get())
    }

    viewModel {
        CreateCategoryViewModel(addCategory = get(), savePicture = get(), router = get())
    }
    viewModel<BaseCategoryScreenViewModel> {
        CategoryScreenViewModel(getCategoryWithNotesWithCategoryId = get(), getGroupedNotes = get(), router = get())
    }

    viewModel { CreateNotesViewModel(addNote = get(), router = get()) }

    viewModel { CalendarViewModel(getAllNotes = get(), getGroupedNotes = get(), router = get()) }

    viewModel { NoteScreenViewModel(getNoteWithId = get(), updateNoteUseCase = get()) }
}