package uz.suhrob.notesapp.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val appModule = module {
    single<CoroutineContext> {
        Dispatchers.Main
    }
}