package uz.suhrob.notesapp.data.database.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import uz.suhrob.notesapp.data.database.CategoryEntity
import uz.suhrob.notesapp.data.database.GetNotesWithCategory
import uz.suhrob.notesapp.data.database.NoteDb
import uz.suhrob.notesapp.data.database.NoteEntity

class NoteDao(
    private val database: NoteDb,
) {
    private val queries = database.noteDbQueries

    fun addCategory(category: CategoryEntity) {
        queries.addCategory(category.title, category.color)
    }

    fun updateCategory(category: CategoryEntity) {
        queries.updateCategory(category.title, category.color, category.id)
    }

    fun removeCategory(category: CategoryEntity) {
        queries.removeCategory(category.id)
    }

    fun getCategories(): Flow<List<CategoryEntity>> {
        return queries.getCategories().asFlow().mapToList()
    }

    fun addNote(note: NoteEntity) {
        queries.addNote(note.title, note.content, note.date, note.categoryId)
    }

    fun updateNote(note: NoteEntity) {
        queries.updateNote(note.title, note.content, note.categoryId, note.id)
    }

    fun removeNote(note: NoteEntity) {
        queries.removeNote(note.id)
    }

    fun getNotesWithCategory(): Flow<List<GetNotesWithCategory>> {
        return queries.getNotesWithCategory().asFlow().mapToList()
    }
}