package com.secbreel.notes.ui.screens.categories_list

import com.secbreel.notes.model.CategoryWithNotes
import com.secbreel.notes.ui.screens.initial.BaseCategoriesListViewModel
import com.secbreel.notes.ui.screens.initial.IInitialRouter
import com.secbreel.notes.usecases.GetCategoriesUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class CategoriesListViewModel(
    getCategoriesUseCase: GetCategoriesUseCase,
    private val router: IInitialRouter
) : BaseCategoriesListViewModel() {
    //TODO заменить getCategoriesUseCase на observable
    //TODO добавить абстрактную BaseViewModel и настроить отлавливание событий с view и обработку на viewModel


    override val categories: BehaviorSubject<List<CategoryWithNotes>> = BehaviorSubject.create()

    init {
        getCategoriesUseCase()
            .doOnNext { categories.onNext(it) }
            .subscribe()
    }


    override fun attach(input: Input): Observable<*> {
        return with(input) {
            Observable.merge(
                onCategoryClicked
                    .throttleFirst(10, TimeUnit.MILLISECONDS)
                    .doOnNext { router.navigateCategoryScreen(it) },

                onCreateCategoryClicked
                    .throttleFirst(10, TimeUnit.MILLISECONDS)
                    .doOnNext { router.navigateCreateCategory() })
        }
    }
}