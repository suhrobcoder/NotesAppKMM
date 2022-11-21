package uz.suhrob.notesapp.android.ui.pages.add_note

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import uz.suhrob.notesapp.presentation.add_note.confirm_dialog.ConfirmDialog

@Composable
fun ConfirmDialog(
    component: ConfirmDialog,
) {
    AlertDialog(
        onDismissRequest = component::cancel,
        confirmButton = {
            TextButton(onClick = component::discard) {
                Text("Discard", style = TextStyle(color = MaterialTheme.colorScheme.error))
            }
        },
        dismissButton = {
            TextButton(onClick = component::cancel) {
                Text("Cancel")
            }
        },
        title = {
            Text("Discard changes")
        },
        text = {
            Text("You have some changes")
        },
        shape = RoundedCornerShape(size = 16.dp),
    )
}