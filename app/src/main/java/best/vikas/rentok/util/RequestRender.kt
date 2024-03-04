package best.vikas.rentok.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
inline fun <T> RequestStateRender(
    state: State<RequestState<T>>,
    onError: @Composable ((String) -> Unit) = {},
    onLoading: @Composable (() -> Unit) = {},
    onSuccess: @Composable (T) -> Unit,
    noInternet: @Composable (() -> Unit) = {},
) {
    when (val itemValue = state.value) {
        is RequestState.Success -> {
            itemValue.data?.let {
                onSuccess.invoke(
                    it
                )
            }
        }

        is RequestState.Idle -> {

        }

        is RequestState.Error -> {
            onError.invoke(
                itemValue.error
            )
        }

        RequestState.Loading -> onLoading.invoke()
    }
}