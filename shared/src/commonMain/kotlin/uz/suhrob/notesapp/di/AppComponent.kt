package uz.suhrob.notesapp.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.coroutines.CoroutineContext

object AppComponent : KoinComponent {
    val mainContext: CoroutineContext
        get() = get()
}