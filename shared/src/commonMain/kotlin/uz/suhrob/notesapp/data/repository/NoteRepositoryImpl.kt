package uz.suhrob.notesapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.suhrob.notesapp.data.database.dao.NoteDao
import uz.suhrob.notesapp.data.database.mapper.toDomain
import uz.suhrob.notesapp.data.database.mapper.toEntity
import uz.suhrob.notesapp.domain.model.Category
import uz.suhrob.notesapp.domain.model.Note
import uz.suhrob.notesapp.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val noteDao: NoteDao,
) : NoteRepository {

    override fun addCategory(category: Category) {
        noteDao.addCategory(category.toEntity())
    }

    override fun updateCategory(category: Category) {
        noteDao.updateCategory(category.toEntity())
    }

    override fun removeCategory(category: Category) {
        noteDao.removeCategory(category.toEntity())
    }

    override fun getCategories(): Flow<List<Category>> {
        return noteDao.getCategories()
            .map { list -> list.map { category -> category.toDomain() } }
    }

    override fun addNote(note: Note) {
        noteDao.addNote(note.toEntity())
    }

    override fun updateNote(note: Note) {
        noteDao.updateNote(note.toEntity())
    }

    override fun removeNote(note: Note) {
        noteDao.removeNote(note.toEntity())
    }

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotesWithCategory()
            .map { list -> list.map { note -> note.toDomain() } }
    }
}