package uz.suhrob.notesapp.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.essenty.parcelable.WriteWith
import kotlinx.datetime.LocalDateTime
import uz.suhrob.notesapp.domain.util.LocalDateTimeParceler

@Parcelize
data class Note(
    val id: Long,
    val title: String,
    val content: String,
    val date: @WriteWith<LocalDateTimeParceler> LocalDateTime,
    val category: Category,
): Parcelable