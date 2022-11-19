package uz.suhrob.notesapp.android.ui.pages.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import uz.suhrob.notesapp.android.ui.components.CustomTextField
import uz.suhrob.notesapp.presentation.categories.new_category.NewCategory
import uz.suhrob.notesapp.presentation.categories.new_category.colors
import uz.suhrob.notesapp.util.parseColor

@Composable
fun NewCategoryDialog(
    component: NewCategory,
) {
    val state by component.state.subscribeAsState()
    AlertDialog(
        onDismissRequest = component::onDismissClicked,
        confirmButton = {
            TextButton(onClick = component::onSubmitted) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = component::onDismissClicked) {
                Text("Cancel")
            }
        },
        title = {
            Text("New Category")
        },
        text = {
            Column {
                CustomTextField(
                    value = state.title,
                    onChange = component::onTitleChanged,
                    placeholder = "Category name",
                    textStyle = MaterialTheme.typography.titleMedium,
                )
                colors.chunked(4).forEach { chunk ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(space = 4.dp),
                        modifier = Modifier.padding(vertical = 4.dp),
                    ) {
                        chunk.forEach { color ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .clip(RoundedCornerShape(size = 8.dp))
                                    .background(color = Color(parseColor(color)))
                                    .clickable { component.colorClicked(color) },
                                contentAlignment = Alignment.Center,
                            ) {
                                if (color == state.color) {
                                    Icon(
                                        imageVector = Icons.Rounded.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(32.dp),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        shape = RoundedCornerShape(size = 16.dp),
    )
}