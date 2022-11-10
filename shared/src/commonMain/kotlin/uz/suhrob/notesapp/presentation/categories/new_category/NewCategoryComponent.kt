package uz.suhrob.notesapp.presentation.categories.new_category

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import uz.suhrob.notesapp.domain.model.Category
import uz.suhrob.notesapp.domain.repository.NoteRepository

class NewCategoryComponent(
    componentContext: ComponentContext,
    private val noteRepository: NoteRepository,
    private val onDismissed: () -> Unit,
) : NewCategory, ComponentContext by componentContext {

    private val _state = MutableValue(NewCategory.NewCategoryState())
    override val state: Value<NewCategory.NewCategoryState> = _state

    override fun onDismissClicked() {
        onDismissed()
    }

    override fun onSubmitted() {
        val state = state.value
        noteRepository.addCategory(
            Category(
                id = 0L,
                title = state.title,
                color = state.color,
            )
        )
        onDismissed()
    }

    override fun onTitleChanged(text: String) {
        _state.reduce { it.copy(title = text) }
    }

    override fun colorClicked(color: String) {
        _state.reduce { it.copy(color = color) }
    }
}