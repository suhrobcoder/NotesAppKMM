package uz.suhrob.notesapp.android.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.suhrob.notesapp.android.ui.theme.NotesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownMenu(
    value: T,
    options: List<T>,
    itemToText: (T) -> String,
    label: String,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionsSource: MutableInteractionSource = remember { MutableInteractionSource() }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        BasicTextField(
            readOnly = true,
            value = itemToText(value),
            onValueChange = { },
            decorationBox = @Composable { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = itemToText(value),
                    visualTransformation = VisualTransformation.None,
                    enabled = true,
                    innerTextField = innerTextField,
                    interactionSource = interactionsSource,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp),
                    label = { Text(label) },
                    singleLine = true,
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
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
            label = "",
            onItemSelected = { value = it },
        )
    }
}