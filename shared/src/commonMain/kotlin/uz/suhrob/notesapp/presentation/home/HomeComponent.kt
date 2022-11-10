package uz.suhrob.notesapp.presentation.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import uz.suhrob.notesapp.domain.model.Note
import uz.suhrob.notesapp.domain.repository.NoteRepository
import uz.suhrob.notesapp.presentation.categories.CategoriesComponent
import uz.suhrob.notesapp.presentation.notes.NotesComponent
import kotlin.coroutines.CoroutineContext

class HomeComponent(
    componentContext: ComponentContext,
    private val noteRepository: NoteRepository,
    private val mainContext: CoroutineContext,
    private val navigateToAddNotePage: (Note?) -> Unit,
) : Home, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        initialStack = { listOf(Config.Notes) },
        childFactory = ::child,
        handleBackButton = true,
    )
    override val childStack: Value<ChildStack<*, Home.Child>> = stack

    override val currentTab: Value<Home.Tab> = childStack.map {
        when (it.active.instance) {
            is Home.Child.NotesChild -> Home.Tab.Notes
            is Home.Child.CategoriesChild -> Home.Tab.Categories
        }
    }

    override fun tabClick(tab: Home.Tab) {
        navigation.bringToFront(
            when (tab) {
                Home.Tab.Notes -> Config.Notes
                Home.Tab.Categories -> Config.Categories
            }
        )
    }

    private fun child(config: Config, componentContext: ComponentContext): Home.Child {
        return when (config) {
            Config.Notes -> Home.Child.NotesChild(
                NotesComponent(
                    componentContext,
                    noteRepository = noteRepository,
                    mainContext = mainContext,
                    navigateToAddNotePage = navigateToAddNotePage,
                )
            )
            Config.Categories -> Home.Child.CategoriesChild(
                CategoriesComponent(
                    componentContext,
                    noteRepository = noteRepository,
                    mainContext = mainContext,
                )
            )
        }
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        object Notes : Config

        @Parcelize
        object Categories : Config
    }
}