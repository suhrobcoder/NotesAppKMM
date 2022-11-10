package uz.suhrob.notesapp.presentation.add_note

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import uz.suhrob.notesapp.domain.model.Category
import uz.suhrob.notesapp.domain.model.Note
import uz.suhrob.notesapp.domain.repository.NoteRepository
import uz.suhrob.notesapp.util.coroutineScope
import kotlin.coroutines.CoroutineContext

class AddNoteComponent(
    componentContext: ComponentContext,
    private val note: Note?,
    private val noteRepository: NoteRepository,
    mainContext: CoroutineContext,
    private val navigateBack: () -> Unit,
) : AddNote, ComponentContext by componentContext {

    private val _state = MutableValue(
        AddNote.AddNoteState(
            title = note?.title ?: "",
            content = note?.content ?: "",
            date = note?.date ?: Clock.System.now().toLocalDateTime(TimeZone.UTC),
            category = note?.category,
        )
    )
    override val state: Value<AddNote.AddNoteState> = _state

    private val scope = coroutineScope(mainContext)

    init {
        scope.launch {
            val categories = noteRepository.getCategories().first()
            _state.reduce { it.copy(categories = categories) }
        }
    }

    override fun navigateBack() {
        navigateBack.invoke()
    }

    override fun categorySelect(category: Category) {
        _state.reduce { it.copy(category = category) }
    }

    override fun titleChanged(text: String) {
        _state.reduce { it.copy(title = text) }
    }

    override fun contentChanged(text: String) {
        _state.reduce { it.copy(content = text) }
    }

    override fun save() {
        val state = state.value
        if (state.category == null) {
            return
        }
        val newNote = Note(
            id = note?.id ?: 0,
            title = state.title,
            content = state.content,
            date = state.date,
            category = state.category,
        )
        if (note == null) {
            noteRepository.addNote(newNote)
        } else {
            noteRepository.updateNote(newNote)
        }
        navigateBack()
    }
}