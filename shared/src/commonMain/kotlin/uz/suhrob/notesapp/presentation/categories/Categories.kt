package uz.suhrob.notesapp.presentation.categories

import com.arkivanov.decompose.router.overlay.ChildOverlay
import com.arkivanov.decompose.value.Value
import uz.suhrob.notesapp.domain.model.Category
import uz.suhrob.notesapp.presentation.categories.new_category.NewCategory

interface Categories {

    val state: Value<CategoriesState>
    val dialog: Value<ChildOverlay<*, NewCategory>>

    fun showDialog()

    data class CategoriesState(
        val categories: List<Category> = listOf(),
    )
}