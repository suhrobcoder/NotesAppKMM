package uz.suhrob.notesapp.android.ui.pages.add_note

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import uz.suhrob.notesapp.android.ui.components.CustomTextField
import uz.suhrob.notesapp.android.ui.components.DropDownMenu
import uz.suhrob.notesapp.presentation.add_note.AddNote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotePage(
    component: AddNote,
) {
    val state by component.state.subscribeAsState()
    Log.d("AppDebug", state.toString())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "New note")
                },
                navigationIcon = {
                    IconButton(onClick = component::navigateBack) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = component::save) {
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
            DropDownMenu(
                value = state.category,
                options = state.categories,
                itemToText = { it.title },
                label = "Category",
                onItemSelected = component::categorySelect,
            )
            CustomTextField(
                value = state.title,
                onChange = component::titleChanged,
                singLine = true,
                placeholder = "Title",
                textStyle = MaterialTheme.typography.titleLarge,
            )
            Text(
                state.date.toString(),
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                value = state.content,
                onChange = component::contentChanged,
                placeholder = "Content",
                textStyle = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}