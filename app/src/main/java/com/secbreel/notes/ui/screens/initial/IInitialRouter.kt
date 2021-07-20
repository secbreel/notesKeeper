package com.secbreel.notes.ui.screens.initial

import android.os.Bundle

interface IInitialRouter {
    fun navigateCategoryScreen(bundle: Bundle)
    fun navigateCreateCategory()
    fun navigateCreateNote(bundle: Bundle)
    fun navigateNoteScreen(bundle: Bundle)
    fun navigateToPreviousScreen()

}