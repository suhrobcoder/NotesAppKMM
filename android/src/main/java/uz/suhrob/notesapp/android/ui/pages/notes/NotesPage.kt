package uz.suhrob.notesapp.android.ui.pages.notes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import uz.suhrob.notesapp.android.ui.components.DateHeader
import uz.suhrob.notesapp.android.ui.components.NoteCard
import uz.suhrob.notesapp.presentation.notes.Notes
import uz.suhrob.notesapp.util.parseColor

@Composable
fun NotesPage(
    component: Notes,
) {
    val state by component.state.subscribeAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            state.notes.forEach {
                item {
                    DateHeader(date = it.key)
                }
                items(it.value) { note ->
                    NoteCard(
                        title = note.title,
                        content = note.content,
                        color = parseColor(note.category.color),
                        category = note.category.title,
                        onClick = { component.openNote(note) },
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = component::newNote,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
        ) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
        }
    }
}