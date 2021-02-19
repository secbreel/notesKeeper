package com.secbreel.notes.usecases

class ClearDataUseCase(
    private val categoryRepositoryClear: CategoryRepositoryClearUseCase,
    private val notesRepositoryClear: NotesRepositoryClearUseCase,
    private val clearPictures: ClearPicturesUseCase
) {
    operator fun invoke() {
        categoryRepositoryClear()
        notesRepositoryClear()
        clearPictures()
    }
}