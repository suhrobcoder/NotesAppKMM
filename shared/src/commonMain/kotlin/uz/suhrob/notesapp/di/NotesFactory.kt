package uz.suhrob.notesapp.di

import com.squareup.sqldelight.logs.LogSqliteDriver
import io.github.aakira.napier.Napier
import uz.suhrob.notesapp.data.database.DatabaseDriverFactory
import uz.suhrob.notesapp.data.database.NoteDb
import uz.suhrob.notesapp.data.database.dao.NoteDao
import uz.suhrob.notesapp.data.repository.NoteRepositoryImpl
import uz.suhrob.notesapp.domain.repository.NoteRepository

class NotesFactory(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val noteDb = createDatabase()

    fun createNoteRepository(): NoteRepository {
        return NoteRepositoryImpl(NoteDao(noteDb))
    }

    private fun createDatabase(): NoteDb {
        val logDriver = LogSqliteDriver(
            databaseDriverFactory.createDriver(),
        ) {
            Napier.d { "Sqldelight $it" }
        }
        return NoteDb.invoke(logDriver)
    }
}