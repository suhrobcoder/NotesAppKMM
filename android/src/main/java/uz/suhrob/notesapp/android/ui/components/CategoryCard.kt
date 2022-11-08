package uz.suhrob.notesapp.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.suhrob.notesapp.android.ui.theme.NotesAppTheme
import uz.suhrob.notesapp.util.parseColor

@Composable
fun CategoryCard(
    title: String,
    color: Long,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable(enabled = onClick != null, onClick = { onClick?.invoke() }),
        colors = CardDefaults.cardColors(containerColor = Color(color)),
        shape = RoundedCornerShape(size = 16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Preview
@Composable
fun CategoryCard_Preview() {
    NotesAppTheme {
        CategoryCard(
            title = "Category",
            color = parseColor("#4FD36C"),
            modifier = Modifier.width(200.dp),
            onClick = {},
        )
    }
}