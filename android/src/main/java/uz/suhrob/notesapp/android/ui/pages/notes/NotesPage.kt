package uz.suhrob.notesapp.android.ui.pages.notes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import uz.suhrob.notesapp.android.ui.components.DateHeader
import uz.suhrob.notesapp.android.ui.components.NoteCard
import uz.suhrob.notesapp.android.ui.theme.NotesAppTheme
import uz.suhrob.notesapp.domain.model.Category
import uz.suhrob.notesapp.domain.model.Note
import uz.suhrob.notesapp.util.parseColor
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun NotesPage(
    navigator: DestinationsNavigator,
) {
    val testNotes = List(20) {
        Note(
            id = 0,
            title = "Title",
            content = "Content",
            date = Instant.fromEpochMilliseconds(
                Clock.System.now().toEpochMilliseconds() - 86400 * 1000 * Random.nextLong(5)
            ).toLocalDateTime(TimeZone.UTC),
            category = Category(id = 0, title = "Category", color = "#4DF8B1")
        )
    }
    val groupedNotes = testNotes.groupBy { it.date.dayOfMonth }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        groupedNotes.forEach {
            stickyHeader {
                DateHeader(date = it.key.toString())
            }
            items(it.value) { note ->
                NoteCard(
                    title = note.title,
                    content = note.content,
                    color = parseColor(note.category.color),
                    category = note.category.title,
                    onClick = {},
                )
            }
        }
    }
}

@Preview
@Composable
fun NotesPage_Preview() {
    NotesAppTheme {
        NotesPage(navigator = EmptyDestinationsNavigator)
    }
}