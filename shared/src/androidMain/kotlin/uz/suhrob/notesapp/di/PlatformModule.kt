package uz.suhrob.notesapp.di

import org.koin.dsl.module
import uz.suhrob.notesapp.data.database.DatabaseDriverFactory

actual fun platformModule() = module {
    single {
        DatabaseDriverFactory(get()).createDriver()
    }
}