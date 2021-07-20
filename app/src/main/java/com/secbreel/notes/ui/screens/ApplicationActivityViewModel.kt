package com.secbreel.notes.ui.screens

import com.secbreel.notes.ui.screens.initial.IInitialRouter
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class ApplicationActivityViewModel(private val router: IInitialRouter) :
    BaseApplicationActivityViewModel() {


    override fun attach(input: Input): Observable<*> {
        return with(input) {
            onReturnClicked
                .throttleFirst(10, TimeUnit.MILLISECONDS)
                .doOnNext {
                    router.navigateToPreviousScreen()
                }
        }
    }

}