package uz.suhrob.notesapp.domain.util

import android.os.Parcel
import com.arkivanov.essenty.parcelable.Parceler
import kotlinx.datetime.*

internal actual object LocalDateTimeParceler : Parceler<LocalDateTime> {
    override fun create(parcel: Parcel): LocalDateTime {
        return Instant.fromEpochSeconds(parcel.readLong()).toLocalDateTime(TimeZone.UTC)
    }

    override fun LocalDateTime.write(parcel: Parcel, flags: Int) {
        parcel.writeLong(toInstant(TimeZone.UTC).epochSeconds)
    }
}