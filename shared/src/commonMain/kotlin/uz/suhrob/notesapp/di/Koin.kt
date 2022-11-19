package uz.suhrob.notesapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        platformModule(),
        appModule,
        notesModule,
    )
}

fun initKoin() = initKoin {}