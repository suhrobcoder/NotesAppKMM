package uz.suhrob.notesapp.android.ui.pages.add_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import uz.suhrob.notesapp.android.ui.components.CustomTextField
import uz.suhrob.notesapp.android.ui.components.DropDownMenu
import uz.suhrob.notesapp.presentation.add_note.AddNote
import uz.suhrob.notesapp.presentation.categories.new_category.colors
import uz.suhrob.notesapp.util.parseColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotePage(
    component: AddNote,
) {
    val state by component.state.subscribeAsState()
    val dialog by component.dialog.subscribeAsState()
    if (dialog.overlay != null) {
        ConfirmDialog(component = dialog.overlay!!.instance)
    }
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
            Row {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(64.dp)
                        .clip(RoundedCornerShape(percent = 50))
                        .background(Color(parseColor(state.category?.color ?: colors.first())))
                )
                CustomTextField(
                    value = state.content,
                    onChange = component::contentChanged,
                    placeholder = "Content",
                    textStyle = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(start = 8.dp),
                )
            }
        }
    }
}