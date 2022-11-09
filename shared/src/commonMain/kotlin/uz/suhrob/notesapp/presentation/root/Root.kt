package uz.suhrob.notesapp.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import uz.suhrob.notesapp.presentation.add_note.AddNote
import uz.suhrob.notesapp.presentation.home.Home

interface Root {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class HomeChild(val component: Home) : Child
        class AddNoteChild(val component: AddNote): Child
    }
}