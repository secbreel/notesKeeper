package com.secbreel.notes.ui

import android.app.Application
import com.secbreel.notes.di.app
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {

            androidLogger(org.koin.core.logger.Level.ERROR)
            androidContext(this@NotesApplication)
            modules(app)
        }
    }
}