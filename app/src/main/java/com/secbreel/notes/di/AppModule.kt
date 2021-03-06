package com.secbreel.notes.di

import androidx.room.Room
import com.secbreel.notes.persistance.CategoryDatabase
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.persistance.NoteDatabase
import com.secbreel.notes.persistance.NoteRepository
import com.secbreel.notes.ui.calendar.CalendarViewModel
import com.secbreel.notes.ui.categories_list.CategoriesListViewModel
import com.secbreel.notes.ui.category_screen.CategoryScreenViewModel
import com.secbreel.notes.ui.create_category.CreateCategoryViewModel
import com.secbreel.notes.ui.create_note.CreateNotesViewModel
import com.secbreel.notes.ui.settings.SettingsFragmentViewModel
import com.secbreel.notes.usecases.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val app = module {
    single { get<CategoryDatabase>().categoryDao() }
    single { get<NoteDatabase>().noteDao() }
    single {
        Room.databaseBuilder(
            androidContext(), CategoryDatabase::class.java,
            "categoriesDatabase"
        ).build()
    }
    single {
        Room.databaseBuilder(
            androidContext(), NoteDatabase::class.java,
            "notesDatabase"
        ).build()
    }

    factory { AddNoteUseCase(noteRepository = get(), categoryRepository = get()) }
    factory { GetCategoriesUseCase(categoryRepository = get()) }
    factory { SavePreferencesUseCase(app = get()) }
    factory { SavePictureUseCase(app = get()) }
    factory { AddCategoryUseCase(categoryRepository = get()) }
    factory { GetNotesWithIdUseCase(noteRepository = get()) }
    factory { GetAllNotesUseCase(noteRepository = get()) }
    factory { GetGroupedNotesUseCase() }
    factory {
        ClearDataUseCase(
            categoryRepository = get(),
            notesRepository = get(),
            app = get()
        )
    }
    factory { CategoryRepository(dao = get()) }
    factory { NoteRepository(dao = get()) }

    viewModel {
        CategoriesListViewModel(
            getCategories = get()
        )
    }
    viewModel {
        SettingsFragmentViewModel(
            clearData = get(),
            savePreferences = get()
        )
    }

    viewModel {
        CreateCategoryViewModel(
            addCategory = get(),
            savePicture = get()
        )
    }
    viewModel {
        CategoryScreenViewModel(getNotesWithId = get(), getGroupedNotes = get())
    }

    viewModel { CreateNotesViewModel(addNote = get()) }

    viewModel { CalendarViewModel(getAllNotes = get(), getGroupedNotes = get()) }

}