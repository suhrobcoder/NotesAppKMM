package uz.suhrob.notesapp.android.ui.pages.add_note

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.suhrob.notesapp.android.ui.components.CustomTextField
import uz.suhrob.notesapp.android.ui.components.DropDownMenu
import uz.suhrob.notesapp.android.ui.theme.NotesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotePage() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "New note")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            var value by remember { mutableStateOf("123") }
            val items = listOf("123", "456", "789")
            DropDownMenu(
                value = value,
                options = items,
                itemToText = { it },
                label = "",
                onItemSelected = { value = it },
            )
            CustomTextField(
                value = "",
                onChange = {},
                singLine = true,
                placeholder = "Title",
                textStyle = MaterialTheme.typography.titleLarge,
            )
            Text(
                "Sun 13, 2022",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                value = "",
                onChange = {},
                placeholder = "Content",
                textStyle = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun AddNotePage_Preview() {
    NotesAppTheme {
        AddNotePage()
    }
}