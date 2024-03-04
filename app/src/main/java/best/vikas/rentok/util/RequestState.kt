package best.vikas.rentok.util

sealed class RequestState<out V> {
    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data class Success<out T>(val data: T?) : RequestState<T>()
    data class Error(val error: String) : RequestState<Nothing>()
}