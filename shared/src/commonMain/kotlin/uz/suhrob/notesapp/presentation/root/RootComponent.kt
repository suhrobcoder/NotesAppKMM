package uz.suhrob.notesapp.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import uz.suhrob.notesapp.di.NotesFactory
import uz.suhrob.notesapp.domain.model.Note
import uz.suhrob.notesapp.presentation.add_note.AddNoteComponent
import uz.suhrob.notesapp.presentation.home.HomeComponent
import kotlin.coroutines.CoroutineContext

class RootComponent(
    componentContext: ComponentContext,
    private val mainContext: CoroutineContext,
    private val notesFactory: NotesFactory,
) : Root, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        initialStack = { listOf(Config.Home) },
        childFactory = ::child,
        handleBackButton = true,
    )
    override val childStack: Value<ChildStack<*, Root.Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Root.Child {
        return when (config) {
            Config.Home -> Root.Child.HomeChild(
                HomeComponent(componentContext,
                    mainContext = mainContext,
                    noteRepository = notesFactory.createNoteRepository(),
                    navigateToAddNotePage = { navigation.push(Config.AddNote(it)) }),
            )
            is Config.AddNote -> Root.Child.AddNoteChild(AddNoteComponent())
        }
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        object Home : Config

        @Parcelize
        class AddNote(val note: Note?) : Config
    }
}