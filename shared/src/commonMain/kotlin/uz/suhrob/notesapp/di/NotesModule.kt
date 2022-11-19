package uz.suhrob.notesapp.di

import org.koin.dsl.module
import uz.suhrob.notesapp.data.database.NoteDb
import uz.suhrob.notesapp.data.database.dao.NoteDao
import uz.suhrob.notesapp.data.repository.NoteRepositoryImpl
import uz.suhrob.notesapp.domain.repository.NoteRepository

val notesModule = module {
    single {
        NoteDb(get())
    }
    factory {
        NoteDao(get())
    }
    factory<NoteRepository> {
        NoteRepositoryImpl(get())
    }
}