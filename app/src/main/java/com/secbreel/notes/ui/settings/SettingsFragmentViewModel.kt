package com.secbreel.notes.ui.settings

import androidx.lifecycle.ViewModel
import com.secbreel.notes.usecases.ClearDataUseCase
import com.secbreel.notes.usecases.SavePreferencesUseCase

class SettingsFragmentViewModel(
    private val clearData: ClearDataUseCase,
    private val savePreferences: SavePreferencesUseCase,
) : ViewModel() {

    fun clearCategories() {
        clearData()
    }

    fun saveThemeMode(theme: Int) = savePreferences.saveTheme(theme)
}