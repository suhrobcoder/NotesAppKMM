package uz.suhrob.notesapp.di

import uz.suhrob.notesapp.data.database.DatabaseDriverFactory
import uz.suhrob.notesapp.data.database.NoteDb
import uz.suhrob.notesapp.data.database.dao.NoteDao
import uz.suhrob.notesapp.data.repository.NoteRepositoryImpl
import uz.suhrob.notesapp.domain.repository.NoteRepository

class NotesFactory(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val noteDb = createDatabase()

    init {
        print("NotesFactory init")
    }

    fun createNoteRepository(): NoteRepository {
        return NoteRepositoryImpl(NoteDao(noteDb))
    }

    private fun createDatabase(): NoteDb {
        return NoteDb.invoke(databaseDriverFactory.createDriver())
    }
}