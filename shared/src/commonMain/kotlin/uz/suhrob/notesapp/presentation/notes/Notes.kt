package uz.suhrob.notesapp.presentation.notes

import com.arkivanov.decompose.value.Value
import uz.suhrob.notesapp.domain.model.Note

interface Notes {

    val state: Value<NotesState>

    fun openNote(note: Note)

    fun newNote()

    data class NotesState(
        val notes: Map<String, List<Note>> = emptyMap(),
    )
}