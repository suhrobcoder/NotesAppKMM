package uz.suhrob.notesapp.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.suhrob.notesapp.domain.model.Category
import uz.suhrob.notesapp.domain.model.Note

interface NoteRepository {
    fun addCategory(category: Category)
    fun updateCategory(category: Category)
    fun removeCategory(category: Category)
    fun getCategories(): Flow<List<Category>>
    fun addNote(note: Note)
    fun updateNote(note: Note)
    fun removeNote(note: Note)
    fun getNotes(): Flow<List<Note>>
}