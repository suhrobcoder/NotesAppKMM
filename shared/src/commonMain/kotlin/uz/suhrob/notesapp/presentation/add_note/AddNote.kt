package uz.suhrob.notesapp.presentation.add_note

import com.arkivanov.decompose.value.Value
import kotlinx.datetime.LocalDateTime
import uz.suhrob.notesapp.domain.model.Category

interface AddNote {

    val state: Value<AddNoteState>

    fun navigateBack()

    fun categorySelect(category: Category)

    fun titleChanged(text: String)

    fun contentChanged(text: String)

    fun save()

    data class AddNoteState(
        val title: String,
        val content: String,
        val date: LocalDateTime,
        val category: Category? = null,
        val categories: List<Category> = listOf(),
    )
}