package com.secbreel.notes.di

import androidx.room.Room
import com.secbreel.notes.persistance.CategoryDatabase
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.ui.CategoriesListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val app = module {
    single { get<CategoryDatabase>().categoryDao() }
    single {
        Room.databaseBuilder(
            androidContext(), CategoryDatabase::class.java,
            "database"
        ).build()
    }
    factory { CategoryRepository(dao = get()) }
    viewModel<CategoriesListViewModel> {
        CategoriesListViewModel(
            repository = get()
        )
    }
}