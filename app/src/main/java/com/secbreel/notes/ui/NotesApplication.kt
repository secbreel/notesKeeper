package com.secbreel.notes.ui

import android.app.Application
import com.secbreel.notes.di.databases.databases
import com.secbreel.notes.di.navigation.navigation
import com.secbreel.notes.di.usecases.useCases
import com.secbreel.notes.di.viewmodels.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {

            androidLogger(org.koin.core.logger.Level.ERROR)
            androidContext(this@NotesApplication)
            modules(listOf(databases, navigation, useCases, viewModels))
        }
    }
}