package uz.suhrob.notesapp.util

fun parseColor(color: String): Long {
    return color.removePrefix("#").toLong(16) or 0x00000000FF000000
}