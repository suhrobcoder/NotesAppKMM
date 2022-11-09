package uz.suhrob.notesapp.domain.util

import com.arkivanov.essenty.parcelable.Parceler
import kotlinx.datetime.LocalDateTime

internal expect object LocalDateTimeParceler : Parceler<LocalDateTime>