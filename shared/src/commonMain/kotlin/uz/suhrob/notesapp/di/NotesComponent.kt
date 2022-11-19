package uz.suhrob.notesapp.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import uz.suhrob.notesapp.domain.repository.NoteRepository

object NotesComponent : KoinComponent {
    val noteRepository: NoteRepository
        get() = get()
}