package com.secbreel.notes.di.usecases

import com.secbreel.notes.usecases.*
import org.koin.dsl.module

val useCases = module {

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

    factory { GetNoteWithIdUseCase(categoryRepository = get()) }

    factory { UpdateNoteUseCase(noteRepository = get()) }

}