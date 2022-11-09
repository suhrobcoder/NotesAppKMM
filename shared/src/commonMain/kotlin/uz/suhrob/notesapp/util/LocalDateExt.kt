package uz.suhrob.notesapp.util

import kotlinx.datetime.*

fun LocalDate.format(): String {
    val today = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
    val diff = today - this
    if (diff.days == 0) {
        return "Today"
    }
    if (diff.days == 1) {
        return "Yesterday"
    }
    return "${today.dayOfMonth}.${today.monthNumber}"
}