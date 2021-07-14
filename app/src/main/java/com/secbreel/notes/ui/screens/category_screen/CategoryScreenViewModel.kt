package com.secbreel.notes.ui.screens.category_screen

import com.secbreel.notes.model.ListItem
import com.secbreel.notes.ui.screens.initial.IInitialRouter
import com.secbreel.notes.usecases.GetCategoryWithNotesWithCategoryIdUseCase
import com.secbreel.notes.usecases.GetGroupedNotesUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CategoryScreenViewModel(
    private val getCategoryWithNotesWithCategoryId: GetCategoryWithNotesWithCategoryIdUseCase,
    private val getGroupedNotes: GetGroupedNotesUseCase,
    private val router: IInitialRouter
) : BaseCategoryScreenViewModel() {

    override fun getNotes(id: Int): Observable<List<ListItem>> {
        return getCategoryWithNotesWithCategoryId(id)
            .map { category ->
                getGroupedNotes.invoke(category.notes)
            }.subscribeOn(Schedulers.io())
    }




    override fun attach(input: Input): Observable<*> {
        return with(input) {
            Observable.merge(
                onCreateNoteClicked
                    .throttleFirst(10, TimeUnit.MILLISECONDS)
                    .doOnNext { router.navigateCreateNote(it) },
                onNoteClicked
                    .throttleFirst(10, TimeUnit.MILLISECONDS)
                    .doOnNext { router.navigateNoteScreen(it) }
            )
        }
    }

}