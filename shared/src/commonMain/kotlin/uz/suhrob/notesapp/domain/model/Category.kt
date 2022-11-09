package uz.suhrob.notesapp.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class Category(
    val id: Long,
    val title: String,
    val color: String,
): Parcelable