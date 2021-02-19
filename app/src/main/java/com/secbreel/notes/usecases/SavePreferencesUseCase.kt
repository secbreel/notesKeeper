package com.secbreel.notes.usecases

import android.app.Application
import android.content.Context

class SavePreferencesUseCase(private val app: Application) {
    fun saveTheme(theme: Int) {
        app.getSharedPreferences("preferences", Context.MODE_PRIVATE).edit()
            .putInt("theme", theme).apply()
    }
}
