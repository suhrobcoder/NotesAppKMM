package uz.suhrob.notesapp.presentation.categories.new_category

import com.arkivanov.decompose.value.Value

interface NewCategory {

    val state: Value<NewCategoryState>

    fun onDismissClicked()

    fun onSubmitted()

    fun onTitleChanged(text: String)

    fun colorClicked(color: String)

    data class NewCategoryState(
        val title: String = "",
        val color: String = colors.first(),
    )
}

val colors =
    listOf("#DFFF00", "#FFBF00", "#FF7F50", "#DE3163", "#9FE2BF", "#40E0D0", "#6495ED", "#CCCCFF")