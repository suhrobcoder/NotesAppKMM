package uz.suhrob.notesapp.data.database.mapper

import kotlinx.datetime.*
import uz.suhrob.notesapp.data.database.CategoryEntity
import uz.suhrob.notesapp.data.database.GetNotesWithCategory
import uz.suhrob.notesapp.data.database.NoteEntity
import uz.suhrob.notesapp.domain.model.Category
import uz.suhrob.notesapp.domain.model.Note

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        date = date.toInstant(TimeZone.UTC).toEpochMilliseconds(),
        categoryId = category.id,
    )
}

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        title = title,
        color = color,
    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        title = title,
        color = color,
    )
}

fun GetNotesWithCategory.toDomain(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        date = Instant.fromEpochSeconds(date).toLocalDateTime(TimeZone.UTC),
        category = Category(
            id = id_!!,
            title = title_!!,
            color = color!!,
        )
    )
}