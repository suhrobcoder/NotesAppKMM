package uz.suhrob.notesapp.util

data class Event<T>(
    private val data: T,
) {
    private var isDataHandled = false

    fun getDataIfNotHandled(): T? {
        if (isDataHandled) {
            return null
        }
        isDataHandled = true
        return data
    }

    override fun equals(other: Any?): Boolean {
        return data == data && !isDataHandled
    }

    override fun hashCode(): Int {
        var result = data?.hashCode() ?: 0
        result = 31 * result + isDataHandled.hashCode()
        return result
    }
}