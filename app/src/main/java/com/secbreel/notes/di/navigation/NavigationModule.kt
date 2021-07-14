package com.secbreel.notes.di.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.secbreel.notes.ui.screens.InitialRouter
import com.secbreel.notes.ui.screens.initial.IInitialRouter
import org.koin.dsl.module

val navigation = module {

    single { Cicerone.create() }

    single { get<Cicerone<Router>>().router }

    single { get<Cicerone<Router>>().getNavigatorHolder() }

    single <IInitialRouter> { InitialRouter(router = get()) }
}