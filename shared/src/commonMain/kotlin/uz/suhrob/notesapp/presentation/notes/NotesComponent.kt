package uz.suhrob.notesapp.presentation.notes

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.suhrob.notesapp.domain.model.Note
import uz.suhrob.notesapp.domain.repository.NoteRepository
import uz.suhrob.notesapp.util.coroutineScope
import uz.suhrob.notesapp.util.format
import kotlin.coroutines.CoroutineContext

class NotesComponent(
    componentContext: ComponentContext,
    private val noteRepository: NoteRepository,
    mainContext: CoroutineContext,
    private val navigateToAddNotePage: (Note?) -> Unit,
) : Notes, ComponentContext by componentContext {

    private val _state = MutableValue(Notes.NotesState())
    override val state: Value<Notes.NotesState> = _state

    private val scope = coroutineScope(mainContext + SupervisorJob())

    init {
        scope.launch {
            noteRepository.getNotes().collectLatest { notes ->
                val map = notes.groupBy { note -> note.date.date }
                val sortedMap: MutableMap<String, List<Note>> = LinkedHashMap()
                map.entries.sortedByDescending { it.key }
                    .forEach { sortedMap[it.key.format()] = it.value }
                _state.reduce { it.copy(notes = sortedMap) }
            }
        }
    }

    override fun openNote(note: Note) {
        navigateToAddNotePage(note)
    }

    override fun newNote() {
        navigateToAddNotePage(null)
    }
}