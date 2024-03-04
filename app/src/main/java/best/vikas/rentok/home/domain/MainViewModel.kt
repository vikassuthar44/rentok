package best.vikas.rentok.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import best.vikas.rentok.home.data.UserDetailsResponse
import best.vikas.rentok.home.data.UserListResponse
import best.vikas.rentok.network.DataSourceRepository
import best.vikas.rentok.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataSourceRepository: DataSourceRepository,
) : ViewModel() {

    private val _userList =
        MutableStateFlow<RequestState<List<UserListResponse>>>(RequestState.Idle)
    val userList = _userList.asStateFlow()

    private val _userDetails =
        MutableStateFlow<RequestState<UserDetailsResponse>>(RequestState.Idle)
    val userDetail = _userDetails.asStateFlow()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        _userList.value = RequestState.Loading
        viewModelScope.launch {
            try {
                dataSourceRepository.fetchUsers()
                    .flowOn(Dispatchers.IO)
                    .catch {
                        _userList.value = RequestState.Error("Something Went Wrong")
                    }
                    .collect {
                        _userList.value = RequestState.Success(it)
                    }
            } catch (e: Exception) {
                _userList.value = RequestState.Error("Something Went Wrong")
            }

        }
    }

    fun userDetails(userName: String) {
        _userDetails.value = RequestState.Loading
        viewModelScope.launch {
            try {
                dataSourceRepository.userDetail(userName = userName)
                    .flowOn(Dispatchers.IO)
                    .catch {
                        _userDetails.value = RequestState.Error("Something Went Wrong")
                    }
                    .collect {
                        _userDetails.value = RequestState.Success(it)
                    }
            } catch (e: Exception) {
                _userDetails.value = RequestState.Error("Something Went Wrong")
            }
        }
    }


}