package uz.suhrob.notesapp.presentation.categories

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.overlay.*
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.suhrob.notesapp.domain.model.Category
import uz.suhrob.notesapp.domain.repository.NoteRepository
import uz.suhrob.notesapp.presentation.categories.new_category.NewCategory
import uz.suhrob.notesapp.presentation.categories.new_category.NewCategoryComponent
import uz.suhrob.notesapp.util.coroutineScope
import kotlin.coroutines.CoroutineContext

class CategoriesComponent(
    componentContext: ComponentContext,
    private val noteRepository: NoteRepository,
    mainContext: CoroutineContext,
) : Categories, ComponentContext by componentContext {

    private val _state = MutableValue(Categories.CategoriesState())
    override val state = _state

    private val dialogNavigation = OverlayNavigation<NewCategoryConfig>()

    private val _dialog = childOverlay(
        source = dialogNavigation,
        handleBackButton = true,
    ) { config, componentContext ->
        NewCategoryComponent(
            componentContext,
            config.category,
            noteRepository = noteRepository,
            onDismissed = dialogNavigation::dismiss,
        )
    }
    override val dialog: Value<ChildOverlay<*, NewCategory>> = _dialog

    private val scope = coroutineScope(mainContext + SupervisorJob())

    init {
        scope.launch {
            noteRepository.getCategories().collectLatest { categories ->
                _state.reduce { it.copy(categories = categories) }
            }
        }
    }

    override fun showDialog(category: Category?) {
        dialogNavigation.activate(NewCategoryConfig(category))
    }

    @Parcelize
    private class NewCategoryConfig(val category: Category?) : Parcelable
}