package com.secbreel.notes.di

import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.secbreel.notes.persistance.AppDatabase
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.persistance.NoteRepository
import com.secbreel.notes.ui.calendar.CalendarViewModel
import com.secbreel.notes.ui.categories_list.CategoriesListViewModel
import com.secbreel.notes.ui.category_screen.CategoryScreenViewModel
import com.secbreel.notes.ui.create_category.CreateCategoryViewModel
import com.secbreel.notes.ui.create_note.CreateNotesViewModel
import com.secbreel.notes.ui.note_screen.NoteScreenViewModel
import com.secbreel.notes.ui.settings.SettingsFragmentViewModel
import com.secbreel.notes.usecases.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val app = module {
    //TODO декомпозировать
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<AppDatabase>().categoryDao() }
    single { get<AppDatabase>().noteDao() }
    single {
        Room.databaseBuilder(
            androidContext(), AppDatabase::class.java,
            "appDatabase"
        ).build()
    }

    factory { AddNoteUseCase(noteRepository = get(), categoryRepository = get()) }
    factory { GetCategoriesUseCase(categoryRepository = get()) }
    factory { SavePreferencesUseCase(app = get()) }
    factory { SavePictureUseCase(app = get()) }
    factory { AddCategoryUseCase(categoryRepository = get()) }
    factory { GetCategoryWithNotesWithCategoryIdUseCase(categoryRepository = get()) }
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
    factory { GetNoteWithIdUseCase(categoryRepository = get()) }
    factory { UpdateNoteUseCase(noteRepository = get()) }

    viewModel {
        CategoriesListViewModel(
            getCategoriesUseCase = get()
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
        CategoryScreenViewModel(getCategoryWithNotesWithCategoryId = get(), getGroupedNotes = get())
    }

    viewModel { CreateNotesViewModel(addNote = get()) }

    viewModel { CalendarViewModel(getAllNotes = get(), getGroupedNotes = get()) }

    viewModel { NoteScreenViewModel(getNoteWithId = get(), updateNoteUseCase = get()) }

}