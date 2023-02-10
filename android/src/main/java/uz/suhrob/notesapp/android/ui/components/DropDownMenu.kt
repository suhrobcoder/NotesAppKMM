package uz.suhrob.notesapp.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.suhrob.notesapp.android.ui.theme.NotesAppTheme

@Composable
fun <T> DropDownMenu(
    value: T?,
    options: List<T>,
    itemToText: (T) -> String,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val valueText = if (value != null) itemToText(value) else ""
    Box(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                valueText,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Rounded.KeyboardArrowUp
                    else Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(itemToText(option)) },
                    onClick = {
                        onItemSelected(option)
                        expanded = false
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun DropDownMenu_Preview() {
    NotesAppTheme {
        var value by remember { mutableStateOf("123") }
        val items = listOf("123", "456", "789")
        DropDownMenu(
            value = value,
            options = items,
            itemToText = { it },
            onItemSelected = { value = it },
        )
    }
}