package uz.suhrob.notesapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import kotlinx.coroutines.Dispatchers
import uz.suhrob.notesapp.android.ui.pages.add_note.AddNotePage
import uz.suhrob.notesapp.android.ui.pages.home.HomePage
import uz.suhrob.notesapp.android.ui.theme.NotesAppTheme
import uz.suhrob.notesapp.data.database.DatabaseDriverFactory
import uz.suhrob.notesapp.di.NotesFactory
import uz.suhrob.notesapp.presentation.root.Root
import uz.suhrob.notesapp.presentation.root.RootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootComponent = RootComponent(
            defaultComponentContext(),
            mainContext = Dispatchers.Main,
            notesFactory = NotesFactory(DatabaseDriverFactory(applicationContext)),
        )
        setContent {
            NotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesApp(rootComponent)
                }
            }
        }
    }
}

@Suppress("OPT_IN_USAGE")
@Composable
fun NotesApp(component: Root) {
    val childStack by component.childStack.subscribeAsState()
    Children(stack = childStack, animation = stackAnimation(slide())) {
        when (val child = it.instance) {
            is Root.Child.HomeChild -> HomePage(component = child.component)
            is Root.Child.AddNoteChild -> AddNotePage(component = child.component)
        }
    }
}