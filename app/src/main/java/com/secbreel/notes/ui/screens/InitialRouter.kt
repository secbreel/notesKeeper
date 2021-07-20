package com.secbreel.notes.ui.screens

import android.os.Bundle
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.secbreel.notes.ui.screens.category_screen.CategoryScreenFragment
import com.secbreel.notes.ui.screens.create_category.CreateCategoryFragment
import com.secbreel.notes.ui.screens.create_note.CreateNotesFragment
import com.secbreel.notes.ui.screens.initial.IInitialRouter
import com.secbreel.notes.ui.screens.note_screen.NoteScreenFragment

class InitialRouter(private val router: Router) : IInitialRouter {

    override fun navigateCategoryScreen(bundle: Bundle) {
        router.navigateTo(FragmentScreen {
            CategoryScreenFragment().apply {
                arguments = bundle
            }
        })
    }

    override fun navigateCreateCategory() {
        router.navigateTo(
            FragmentScreen {
                CreateCategoryFragment()
            })
    }

    override fun navigateCreateNote(bundle: Bundle) {
        router.navigateTo(FragmentScreen {
            CreateNotesFragment().apply {
                arguments = bundle
            }
        })
    }

    override fun navigateNoteScreen(bundle: Bundle) {
        router.navigateTo(FragmentScreen {
            NoteScreenFragment().apply {
                arguments = bundle
            }
        })
    }

    override fun navigateToPreviousScreen() {
        router.exit()
    }
}