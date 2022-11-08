package uz.suhrob.notesapp.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import uz.suhrob.notesapp.presentation.home.HomeComponent

class RootComponent(
    componentContext: ComponentContext,
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
        return when(config) {
            Config.Home -> Root.Child.HomeChild(HomeComponent(componentContext))
        }
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        object Home : Config
    }
}