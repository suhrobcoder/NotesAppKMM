package uz.suhrob.notesapp.domain.model

import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long,
    val title: String,
    val content: String,
    val date: LocalDateTime,
    val category: Category,
)