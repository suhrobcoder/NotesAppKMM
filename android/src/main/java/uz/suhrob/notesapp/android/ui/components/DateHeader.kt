package uz.suhrob.notesapp.android.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.suhrob.notesapp.android.ui.theme.NotesAppTheme

@Composable
fun DateHeader(
    date: String,
) {
    Text(
        text = date,
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier.padding(vertical = 8.dp),
    )
}

@Preview
@Composable
fun DateHeader_Preview() {
    NotesAppTheme {
        DateHeader("November 5")
    }
}