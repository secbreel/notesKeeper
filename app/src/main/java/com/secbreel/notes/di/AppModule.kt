package com.secbreel.notes.di

import androidx.room.Room
import com.secbreel.notes.persistance.CategoryDatabase
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.persistance.NoteDatabase
import com.secbreel.notes.persistance.NoteRepository
import com.secbreel.notes.ui.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val app = module {
    single { get<CategoryDatabase>().categoryDao() }
    single { get<NoteDatabase>().noteDao()}
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
    factory { CategoryRepository(dao = get()) }
    factory { NoteRepository(dao = get()) }

    viewModel<CategoriesListViewModel> {
        CategoriesListViewModel(
            repository = get()
        )
    }
    viewModel<SettingsFragmentViewModel> {
        SettingsFragmentViewModel(
            categoryRepository = get(),
            notesRepository = get(),
            app = get()
        )
    }

    viewModel<CreateCategoryViewModel> {
        CreateCategoryViewModel(
            repository = get(),
            app = get()
        )
    }
    viewModel<CategoryScreenViewModel> {
        CategoryScreenViewModel(notesRepository = get(), categoryRepository = get())
    }

    viewModel<CreateNotesViewModel> { CreateNotesViewModel(repository = get()) }
}