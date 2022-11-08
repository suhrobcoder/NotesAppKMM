package uz.suhrob.notesapp.android.ui.pages.categories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.suhrob.notesapp.android.ui.components.CategoryCard
import uz.suhrob.notesapp.android.ui.theme.NotesAppTheme
import uz.suhrob.notesapp.domain.model.Category
import uz.suhrob.notesapp.util.parseColor

@Composable
fun CategoriesPage() {
    val testCategories = List(10) {
        Category(0, "Category $it", "#F43CD3")
    }
    Box {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(testCategories) { category ->
                CategoryCard(
                    title = category.title,
                    color = parseColor(category.color),
                    onClick = {},
                )
            }
        }
        FloatingActionButton(
            onClick = {},
            modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp),
        ) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun CategoriesPage_Prev() {
    NotesAppTheme {
        CategoriesPage()
    }
}