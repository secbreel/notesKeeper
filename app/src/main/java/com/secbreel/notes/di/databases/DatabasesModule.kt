package com.secbreel.notes.di.databases

import androidx.room.Room
import com.secbreel.notes.persistance.AppDatabase
import com.secbreel.notes.persistance.CategoryRepository
import com.secbreel.notes.persistance.NoteRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databases = module {

    single { get<AppDatabase>().categoryDao() }

    single { get<AppDatabase>().noteDao() }

    single {
        Room.databaseBuilder(
            androidContext(), AppDatabase::class.java,
            "appDatabase")
            .build()
    }

    factory { CategoryRepository(dao = get()) }

    factory { NoteRepository(dao = get()) }
}