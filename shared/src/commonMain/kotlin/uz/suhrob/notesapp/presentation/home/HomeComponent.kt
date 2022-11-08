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
import uz.suhrob.notesapp.presentation.notes.NotesComponent

class HomeComponent(
    componentContext: ComponentContext,
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
        }
    }

    override fun tabClick(tab: Home.Tab) {
        navigation.bringToFront(
            when (tab) {
                Home.Tab.Notes -> Config.Notes
            }
        )
    }

    private fun child(config: Config, componentContext: ComponentContext): Home.Child {
        return when (config) {
            Config.Notes -> Home.Child.NotesChild(NotesComponent())
        }
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        object Notes : Config
    }
}