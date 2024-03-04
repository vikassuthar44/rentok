package best.vikas.rentok.network

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService,
) : ApiHelper {
    override suspend fun fetchUsers() = flow {
        emit(apiService.fetchUser())
    }

    override suspend fun userDetails(userName: String) = flow {
        emit(apiService.userDetails(userName = userName))
    }
}