package uz.suhrob.notesapp.presentation.add_note

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.overlay.*
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import uz.suhrob.notesapp.domain.model.Category
import uz.suhrob.notesapp.domain.model.Note
import uz.suhrob.notesapp.domain.repository.NoteRepository
import uz.suhrob.notesapp.presentation.add_note.confirm_dialog.ConfirmDialog
import uz.suhrob.notesapp.presentation.add_note.confirm_dialog.ConfirmDialogComponent
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

    private val dialogNavigation = OverlayNavigation<ConfirmDialogConfig>()

    private val _dialog = childOverlay(
        source = dialogNavigation,
        handleBackButton = true,
    ) { _, componentContext ->
        ConfirmDialogComponent(
            componentContext,
            onCancel = dialogNavigation::dismiss,
            onDiscard = navigateBack,
        )
    }
    override val dialog: Value<ChildOverlay<*, ConfirmDialog>> = _dialog

    private val backCallback = BackCallback {
        if (isNoteChanged()) {
            dialogNavigation.activate(ConfirmDialogConfig)
        } else {
            navigateBack()
        }
    }

    init {
        scope.launch {
            val categories = noteRepository.getCategories().first()
            _state.reduce { it.copy(categories = categories) }
        }
        backHandler.register(backCallback)
    }

    override fun navigateBack() {
        backCallback.onBack()
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

    private fun isNoteChanged(): Boolean {
        val state = state.value
        if (note == null && (state.title.isNotEmpty() || state.content.isNotEmpty())) {
            return true
        }
        if (note != null) {
            if (note.title != state.title || note.content != state.content || note.category != state.category) {
                return true
            }
        }
        return false
    }

    @Parcelize
    private object ConfirmDialogConfig : Parcelable
}