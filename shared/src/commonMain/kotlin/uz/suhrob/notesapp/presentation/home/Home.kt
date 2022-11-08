package uz.suhrob.notesapp.presentation.home

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import uz.suhrob.notesapp.presentation.notes.Notes

interface Home {
    val childStack: Value<ChildStack<*, Child>>
    val currentTab: Value<Tab>

    fun tabClick(tab: Tab)

    sealed interface Child {
        class NotesChild(val component: Notes) : Child
    }

    enum class Tab(val title: String) {
        Notes("Notes"),
    }
}
